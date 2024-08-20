package com.easyhz.noffice.feature.announcement.screen.creation.promotion

import android.net.Uri
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.domain.announcement.usecase.announcement.CreateAnnouncementUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetDrawableUriUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UploadImageUseCase
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.CardImage
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromotionViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getDrawableUriUseCase: GetDrawableUriUseCase,
    private val createAnnouncementUseCase: CreateAnnouncementUseCase
): BaseViewModel<PromotionState, PromotionIntent, PromotionSideEffect>(
    initialState = PromotionState.init()
) {
    override fun handleIntent(intent: PromotionIntent) {
        when(intent) {
            is PromotionIntent.InitScreen -> { initScreen(intent.param) }
            is PromotionIntent.ClickBackButton -> { onClickBackButton() }
            is PromotionIntent.ClickSaveButton -> { saveButton() }
            is PromotionIntent.ClickPromotionCard -> { onClickPromotionCard(intent.cardImage) }
            is PromotionIntent.HideUserNameBottomSheet -> { hideBottomSheet() }
            is PromotionIntent.SetPromotionBottomSheet -> { setBottomSheet(intent.isShow) }
            is PromotionIntent.ClickBottomSheetCard -> { onClickBottomSheetCard(intent.cardImage) }
            is PromotionIntent.ClickBottomSheetSelectButton -> { onClickBottomSheetSelectButton() }
        }
    }

    private fun initScreen(param: AnnouncementParam) {
        reduce { copy(announcementParam = param) }
    }

    private fun onClickBackButton() {
        postSideEffect { PromotionSideEffect.NavigateToUp }
    }

    private fun onClickPromotionCard(cardImage: CardImage) {
        if (currentState.selectCard == cardImage) return
        if (!currentState.hasPromotion && cardImage.isPromotion) {
            setBottomSheet(true)
        } else {
            reduce { copy(selectCard = cardImage, bottomSheetSelectCard = cardImage) }
            scrollToItem()
        }
    }

    private fun onClickBottomSheetCard(cardImage: CardImage) {
        if (currentState.bottomSheetSelectCard == cardImage) return
        reduce { copy(bottomSheetSelectCard = cardImage) }
    }

    private fun onClickBottomSheetSelectButton() {
        hideBottomSheet()
        reduce { copy(selectCard = bottomSheetSelectCard) }
        scrollToItem()
    }

    private fun hideBottomSheet() {
        postSideEffect { PromotionSideEffect.HidePromotionBottomSheet }
    }

    private fun setBottomSheet(isShow: Boolean) {
        reduce { copy(isShowPromotionBottomSheet = isShow) }
    }

    private fun scrollToItem() {
        val index = CardImage.entries.indexOf(currentState.selectCard)
        postSideEffect { PromotionSideEffect.ScrollToItem(index) }
    }

    private fun saveButton() = viewModelScope.launch {
        setIsLoading(true)
        val imageUri = getDrawableUri() ?: return@launch
        val imageUrl = uploadImage(imageUri) ?: return@launch
        val param = currentState.announcementParam?.copy(
            profileImageUrl = imageUrl
        ) ?: return@launch
        createAnnouncementUseCase.invoke(param).onSuccess {
            postSideEffect { PromotionSideEffect.NavigateToSuccess(it.organizationId, it.title) }
        }.onFailure {
            showSnackBar(it.handleError())
        }.also {
            setIsLoading(false)
        }
    }
    private suspend fun uploadImage(imageUri: Uri): String? {
        val param = ImageParam(uri = imageUri, purpose = ImagePurpose.ANNOUNCEMENT_PROFILE)
        return uploadImageUseCase.invoke(param).getOrElse {
            Log.d(this.javaClass.name, "uploadImage - ${it.message}")
            setIsLoading(false)
            showSnackBar(it.handleError())
            null
        }
    }

    private suspend fun getDrawableUri(): Uri? {
        return getDrawableUriUseCase.invoke(currentState.selectCard.imageId).getOrElse {
            Log.d(this.javaClass.name, "getDrawableUri - ${it.message}")
            setIsLoading(false)
            showSnackBar(it.handleError())
            null
        }
    }
    
    private fun setIsLoading(value: Boolean) {
        reduce { copy(isLoading = value) }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            PromotionSideEffect.ShowSnackBar(stringId)
        }
    }
}