package com.easyhz.noffice.feature.organization.screen.creation

import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.common.util.updateStepButton
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.organization.Organization
import com.easyhz.noffice.core.model.organization.param.OrganizationCreationParam
import com.easyhz.noffice.domain.organization.usecase.category.FetchCategoriesUseCase
import com.easyhz.noffice.domain.organization.usecase.creation.CreateOrganizationUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UploadImageUseCase
import com.easyhz.noffice.domain.organization.usecase.organization.CreateOrganizationDeepLinkUseCase
import com.easyhz.noffice.domain.organization.usecase.promotion.VerifyPromotionUseCase
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.contract.creation.CreationSideEffect
import com.easyhz.noffice.feature.organization.contract.creation.CreationState
import com.easyhz.noffice.feature.organization.contract.creation.CreationState.Companion.ORGANIZATION_NAME_MAX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OrganizationCreationViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase,
    private val createOrganizationUseCase: CreateOrganizationUseCase,
    private val fetchCategoriesUseCase: FetchCategoriesUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val verifyPromotionUseCase: VerifyPromotionUseCase,
    private val createOrganizationDeepLinkUseCase: CreateOrganizationDeepLinkUseCase
) : BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)
    private var debounceJob: Job? = null

    override fun handleIntent(intent: CreationIntent) {
        when (intent) {
            is CreationIntent.ClickBackButton -> {
                onClickBackButton()
            }

            is CreationIntent.ClickNextButton -> {
                onClickNextButton()
            }

            is CreationIntent.ChangeOrganizationNameTextValue -> {
                onChangeOrganizationNameTextValue(intent.text)
            }

            is CreationIntent.ClearOrganizationName -> {
                onClearOrganizationName()
            }

            is CreationIntent.ClearFocus -> {
                onClearFocus()
            }

            is CreationIntent.ClickCategoryItem -> {
                onClickCategoryItem(intent.selectedIndex)
            }

            is CreationIntent.ClickImageView -> {
                onClickImageView()
            }

            is CreationIntent.ClickImageBottomSheetItem -> {
                onClickImageBottomSheetItem(intent.item)
            }

            is CreationIntent.HideImageBottomSheet -> {
                setImageBottomSheet(false)
            }

            is CreationIntent.PickImage -> {
                onPickImage(intent.uri)
            }

            is CreationIntent.TakePicture -> {
                onTakePicture(intent.isUsed)
            }

            is CreationIntent.ChangeEndDate -> {
                onChangeEndDate(intent.date)
            }

            is CreationIntent.ChangePromotionTextValue -> {
                onChangePromotionTextValue(intent.text)
            }

            is CreationIntent.ClearPromotionCode -> {
                onClearPromotionCode()
            }
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
        } ?: createOrganization()
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
        setImageBottomSheet(true)
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
                errorLogging(this.javaClass.name, "navigateToCamera", it)
                showSnackBar(it.handleError())
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

        debounceJob?.cancel()
        if (newText.isBlank()) {
            setEnabledStepButton()
        } else {
            handlePromotionCodeVerification()
        }
    }

    private fun handlePromotionCodeVerification() {
        reduce {
            copy(
                enabledStepButton = enabledStepButton.updateStepButton(step.currentStep, false),
                isLoadingPromotionVerification = true
            )
        }
        debounceJob = viewModelScope.launch {
            delay(1500)
            verifyPromotionCode()
        }
    }

    private suspend fun verifyPromotionCode() {
        if (currentState.promotionCode.isBlank()) {
            reduce { copy(isPromotionCodeValid = true) }
            return
        }

        verifyPromotionUseCase.invoke(currentState.promotionCode).onSuccess { isValid ->
            handleVerificationSuccess(isValid)
        }.onFailure { throwable ->
            handleVerificationFailure(throwable)
        }
    }

    private fun handleVerificationSuccess(isValid: Boolean) {
        reduce {
            copy(
                isPromotionCodeValid = isValid,
                enabledStepButton = enabledStepButton.updateStepButton(step.currentStep, true),
                isLoadingPromotionVerification = false
            )
        }
    }

    private fun handleVerificationFailure(throwable: Throwable) {
        errorLogging(this.javaClass.name, "verifyPromotionCode", throwable)
        reduce {
            copy(
                isLoadingPromotionVerification = false,
                isPromotionCodeValid = false
            )
        }
    }

    private fun onClearPromotionCode() {
        reduce { copy(promotionCode = "") }
        setEnabledStepButton()
    }

    private fun onNavigateToUp() {
        postSideEffect { CreationSideEffect.NavigateToUp }
    }

    private fun setImageBottomSheet(value: Boolean) {
        reduce { copy(isShowImageBottomSheet = value) }
    }

    private fun fetchCategories() = viewModelScope.launch {
        fetchCategoriesUseCase.invoke(Unit).onSuccess {
            reduce { copy(category = it) }
        }.onFailure {
            errorLogging(this.javaClass.name, "fetchCategories", it)
            showSnackBar(it.handleError())
        }.also {
            setIsLoading(false)
        }
    }

    private fun createOrganization() = viewModelScope.launch {
        setIsLoading(true)
        val imageUrl = currentState.organizationImage.takeIf { it != Uri.EMPTY }?.let { uri ->
            uploadImage(uri) ?: run {
                setIsLoading(false)
                return@launch
            }
        }
        val param = OrganizationCreationParam(
            name = currentState.organizationName,
            categoryList = currentState.category.filter { it.isSelected }.map { it.id },
            endAt = currentState.endDate,
            profileImage = imageUrl,
            promotionCode = currentState.promotionCode.ifBlank { null }
        )
        createOrganizationUseCase.invoke(param).onSuccess {
            createOrganizationDeepLink(it)
        }.onFailure {
            errorLogging(this.javaClass.name, "createOrganization", it)
            showSnackBar(it.handleError())
        }.also {
            setIsLoading(false)
        }
    }

    private suspend fun uploadImage(imageUri: Uri): String? {
        val param = ImageParam(uri = imageUri, purpose = ImagePurpose.ORGANIZATION_LOGO)
        return uploadImageUseCase.invoke(param).getOrElse {
            errorLogging(this.javaClass.name, "uploadImage", it)
            showSnackBar(it.handleError())
            null
        }
    }

    private fun onNavigateToInvitation(url: String, profileUrl: String?) = viewModelScope.launch {
        postSideEffect {
            CreationSideEffect.NavigateToInvitation(
                url, profileUrl
            )
        }
    }

    private fun createOrganizationDeepLink(organization: Organization) = viewModelScope.launch {
        createOrganizationDeepLinkUseCase(organization.id)
            .onSuccess { deepLink ->
                onNavigateToInvitation(deepLink.toString(), organization.profileImageUrl)
            }
            .onFailure { throwable ->
                errorLogging(javaClass.simpleName, "onNavigateToInvitation", throwable)
                showSnackBar(throwable.handleError())
            }
    }

    private fun setEnabledStepButton() {
        reduce { copy(enabledStepButton = enabledStepButton.updateStepButton(step.currentStep, true)) }
    }

    private fun setIsLoading(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            CreationSideEffect.ShowSnackBar(stringId)
        }
    }
}