package com.easyhz.noffice.feature.announcement.screen.creation.promotion

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.domain.announcement.usecase.announcement.CreateAnnouncementUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetDrawableUriUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UploadImageUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.FetchSelectableCoverUseCase
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionSideEffect
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromotionViewModel @Inject constructor(
    private val fetchSelectableCoverUseCase: FetchSelectableCoverUseCase,
    private val createAnnouncementUseCase: CreateAnnouncementUseCase
): BaseViewModel<PromotionState, PromotionIntent, PromotionSideEffect>(
    initialState = PromotionState.init()
) {
    override fun handleIntent(intent: PromotionIntent) {
        when(intent) {
            is PromotionIntent.InitScreen -> { initScreen(intent.param) }
            is PromotionIntent.ClickBackButton -> { onClickBackButton() }
            is PromotionIntent.ClickSaveButton -> { saveButton() }
            is PromotionIntent.ClickPromotionCard -> { onClickPromotionCard(intent.index) }
            is PromotionIntent.HideUserNameBottomSheet -> { hideBottomSheet() }
            is PromotionIntent.SetPromotionBottomSheet -> { setBottomSheet(intent.isShow) }
            is PromotionIntent.ClickBottomSheetCard -> { onClickBottomSheetCard(intent.index) }
            is PromotionIntent.ClickBottomSheetSelectButton -> { onClickBottomSheetSelectButton() }
        }
    }

    private fun initScreen(param: AnnouncementParam) {
        reduce { copy(announcementParam = param) }
        fetchSelectableCover(param.organizationId)
    }

    private fun fetchSelectableCover(organizationId: Int) = viewModelScope.launch {
        fetchSelectableCoverUseCase.invoke(organizationId).onSuccess {
            reduce { copy(coverList = it.images.sortedWith(compareBy { it.type == ImagePurpose.PROMOTION_COVER }), isLoading = false) }
        }.onFailure {
            showSnackBar(it.handleError())
            reduce { copy(isLoading = false) }
            errorLogging(this.javaClass.name, "fetchSelectableCover", it)
        }
    }

    private fun onClickBackButton() {
        postSideEffect { PromotionSideEffect.NavigateToUp }
    }

    private fun onClickPromotionCard(index: Int) {
        if (currentState.selectCardIndex == index) return
        if (!currentState.hasPromotion && currentState.coverList[index].type == ImagePurpose.PROMOTION_COVER) {
            setBottomSheet(true)
        } else {
            reduce { copy(selectCardIndex = index, bottomSheetSelectCardIndex = index) }
            scrollToItem()
        }
    }

    private fun onClickBottomSheetCard(index: Int) {
        if (currentState.bottomSheetSelectCardIndex == index) return
        reduce { copy(bottomSheetSelectCardIndex = index) }
    }

    private fun onClickBottomSheetSelectButton() {
        hideBottomSheet()
        reduce { copy(selectCardIndex = bottomSheetSelectCardIndex) }
        scrollToItem()
    }

    private fun hideBottomSheet() {
        postSideEffect { PromotionSideEffect.HidePromotionBottomSheet }
    }

    private fun setBottomSheet(isShow: Boolean) {
        reduce { copy(isShowPromotionBottomSheet = isShow) }
    }

    private fun scrollToItem() {
        postSideEffect { PromotionSideEffect.ScrollToItem(currentState.selectCardIndex) }
    }

    private fun saveButton() = viewModelScope.launch {
        setIsLoading(true)
        val imageUrl = currentState.coverList.getOrNull(currentState.selectCardIndex)?.url ?: return@launch
        val param = currentState.announcementParam?.copy(
            profileImageUrl = imageUrl
        ) ?: return@launch
        createAnnouncementUseCase.invoke(param).onSuccess {
            postSideEffect { PromotionSideEffect.NavigateToSuccess(it.organizationId, it.announcementId, it.title) }
        }.onFailure {
            showSnackBar(it.handleError())
            errorLogging(this.javaClass.name, "saveButton", it)
        }.also {
            setIsLoading(false)
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