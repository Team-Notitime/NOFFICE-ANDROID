package com.easyhz.noffice.feature.organization.screen.creation

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.util.updateStepButton
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.core.model.organization.param.OrganizationCreationParam
import com.easyhz.noffice.domain.organization.usecase.category.FetchCategoriesUseCase
import com.easyhz.noffice.domain.organization.usecase.creation.CreateOrganizationUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.organization.contract.creation.CreationState
import com.easyhz.noffice.feature.organization.contract.creation.CreationState.Companion.ORGANIZATION_NAME_MAX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OrganizationCreationViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase,
    private val createOrganizationUseCase: CreateOrganizationUseCase,
    private val fetchCategoriesUseCase: FetchCategoriesUseCase,
) : BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)

    override fun handleIntent(intent: CreationIntent) {
        when (intent) {
            is CreationIntent.ClickBackButton -> { onClickBackButton() }
            is CreationIntent.ClickNextButton -> { onClickNextButton() }
            is CreationIntent.ChangeOrganizationNameTextValue -> { onChangeOrganizationNameTextValue(intent.text) }
            is CreationIntent.ClearOrganizationName -> { onClearOrganizationName() }
            is CreationIntent.ClearFocus -> { onClearFocus() }
            is CreationIntent.ClickCategoryItem -> { onClickCategoryItem(intent.selectedIndex) }
            is CreationIntent.ClickImageView -> { onClickImageView() }
            is CreationIntent.ClickImageBottomSheetItem -> { onClickImageBottomSheetItem(intent.item) }
            is CreationIntent.HideImageBottomSheet -> { hideImageBottomSheet() }
            is CreationIntent.PickImage -> { onPickImage(intent.uri) }
            is CreationIntent.TakePicture -> { onTakePicture(intent.isUsed) }
            is CreationIntent.ChangeEndDate -> { onChangeEndDate(intent.date) }
            is CreationIntent.ChangePromotionTextValue -> { onChangePromotionTextValue(intent.text) }
            is CreationIntent.ClearPromotionCode -> { onClearPromotionCode() }
        }
    }

    init {
        fetchCategories()
    }

    private fun onClickBackButton() {
        currentState.step.currentStep.beforeStep()?.let { beforeStep ->
            reduce { updateStep(currentStep = beforeStep) }
        } ?: onNavigateToUp()
    }

    private fun onClickNextButton() {
        currentState.step.currentStep.nextStep()?.let { nextStep ->
            reduce { updateStep(currentStep = nextStep) }
            postSideEffect { CreationSideEffect.ClearFocus }
        } ?: onNavigateToInvitation()
    }

    private fun onChangeOrganizationNameTextValue(newText: String) {
        if (newText.length > ORGANIZATION_NAME_MAX) return
        val isEnabledButton = newText.isNotBlank()
        reduce {
            copy(
                organizationName = newText,
                enabledStepButton = enabledStepButton.updateStepButton(
                    step.currentStep,
                    isEnabledButton
                )
            )
        }
    }

    private fun onClearOrganizationName() {
        reduce { copy(organizationName = "") }
    }

    private fun onClearFocus() {
        postSideEffect { CreationSideEffect.ClearFocus }
    }

    private fun onClickCategoryItem(selectedIndex: Int) {
        reduce { updateCategoryItem(selectedIndex) }
    }

    private fun onClickImageView() {
        showImageBottomSheet()
    }

    private fun onClickImageBottomSheetItem(item: ImageSelectionBottomSheetItem) {
        when (item) {
            ImageSelectionBottomSheetItem.GALLERY -> {
                postSideEffect { CreationSideEffect.NavigateToGallery }
            }

            ImageSelectionBottomSheetItem.CAMERA -> {
                navigateToCamera()
            }

            ImageSelectionBottomSheetItem.DELETE -> {
                reduce { copy(organizationImage = Uri.EMPTY, isShowImageBottomSheet = false) }
            }
        }
        if (!currentState.isShowImageBottomSheet) return
        reduce { copy(isShowImageBottomSheet = false) }
    }

    private fun navigateToCamera() = viewModelScope.launch {
        getTakePictureUriUseCase.invoke(Unit)
            .onSuccess {
                takePictureUri.value = it
                postSideEffect { CreationSideEffect.NavigateToCamera(it) }
            }
            .onFailure {
                // TODO fail 처리
                println("fail: $it")
            }
    }

    private fun onPickImage(uri: Uri?) {
        reduce { copy(organizationImage = uri ?: Uri.EMPTY) }
    }

    private fun onTakePicture(isUsed: Boolean) {
        if (!isUsed) return
        reduce { copy(organizationImage = takePictureUri.value) }
    }

    private fun onChangeEndDate(date: LocalDate) {
        reduce { copy(endDate = date.takeIf { it != currentState.endDate }) }
    }

    private fun onChangePromotionTextValue(newText: String) {
        reduce { copy(promotionCode = newText) }
    }

    private fun onClearPromotionCode() {
        reduce { copy(promotionCode = "") }
    }

    private fun onNavigateToUp() {
        postSideEffect { CreationSideEffect.NavigateToUp }
    }

    private fun showImageBottomSheet() {
        reduce { copy(isShowImageBottomSheet = true) }
    }

    private fun hideImageBottomSheet() {
        reduce { copy(isShowImageBottomSheet = false) }
    }

    private fun fetchCategories() = viewModelScope.launch {
        fetchCategoriesUseCase.invoke(Unit).onSuccess {
            println("성공 $it")
        }.onFailure {
            println("실패 $it")
        }
    }


    // TODO: 서버 통신 로직 추가
    private fun onNavigateToInvitation() = viewModelScope.launch {
        val param = OrganizationCreationParam(
            name = currentState.organizationName,
            categoryList = listOf(1), // FIXME
            endAt = currentState.endDate,
            profileImage = currentState.organizationImage.takeIf { it != Uri.EMPTY },
            promotionCode = currentState.promotionCode.ifBlank { null }
        )
        createOrganizationUseCase.invoke(param).onSuccess {
            postSideEffect {
                CreationSideEffect.NavigateToInvitation(
                    "www.noffice/${it.id}",
                    it.profileImageUrl
                )
            }
        }.onFailure {
            // TODO fail 처리
            println("create fail $it")
        }
    }
}