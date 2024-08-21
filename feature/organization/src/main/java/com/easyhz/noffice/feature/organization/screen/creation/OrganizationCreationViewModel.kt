package com.easyhz.noffice.feature.organization.screen.creation

import android.net.Uri
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.Encryption
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
    private val uploadImageUseCase: UploadImageUseCase
) : BaseViewModel<CreationState, CreationIntent, CreationSideEffect>(
    initialState = CreationState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)

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
                hideImageBottomSheet()
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
                Log.d(this.javaClass.name, "navigateToCamera - ${it.message}")
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
            println("성공 $it") // TODO 카테고리 끼우기
        }.onFailure {
            Log.d(this.javaClass.name, "fetchCategories - ${it.message}")
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
            categoryList = listOf(1), // FIXME
            endAt = currentState.endDate,
            profileImage = imageUrl,
            promotionCode = currentState.promotionCode.ifBlank { null }
        )
        createOrganizationUseCase.invoke(param).onSuccess {
            onNavigateToInvitation(it)
        }.onFailure {
            Log.d(this.javaClass.name, "createOrganization - ${it.message}")
            showSnackBar(it.handleError())
        }.also {
            setIsLoading(false)
        }
    }

    private suspend fun uploadImage(imageUri: Uri): String? {
        val param = ImageParam(uri = imageUri, purpose = ImagePurpose.ORGANIZATION_LOGO)
        return uploadImageUseCase.invoke(param).getOrElse {
            Log.d(this.javaClass.name, "uploadImage - ${it.message}")
            showSnackBar(it.handleError())
            null
        }
    }

    private fun onNavigateToInvitation(organization: Organization) = viewModelScope.launch {
        postSideEffect {
            CreationSideEffect.NavigateToInvitation(
                organization.id.toNofficeDeepLink(),
                organization.profileImageUrl
            )
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        reduce { copy(isLoading = isLoading) }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            CreationSideEffect.ShowSnackBar(stringId)
        }
    }

    private fun Int.toNofficeDeepLink(): String {
        val id = Encryption.encrypt(this.toString())
        return "noffice://join?organizationId=$id"
    }
}