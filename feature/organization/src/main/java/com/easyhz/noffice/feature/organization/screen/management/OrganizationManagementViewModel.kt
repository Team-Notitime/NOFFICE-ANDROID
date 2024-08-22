package com.easyhz.noffice.feature.organization.screen.management

import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.param.CategoryParam
import com.easyhz.noffice.domain.organization.usecase.category.FetchCategoriesUseCase
import com.easyhz.noffice.domain.organization.usecase.category.UpdateOrganizationCategoryUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UploadImageUseCase
import com.easyhz.noffice.feature.organization.contract.management.ManagementIntent
import com.easyhz.noffice.feature.organization.contract.management.ManagementSideEffect
import com.easyhz.noffice.feature.organization.contract.management.ManagementState
import com.easyhz.noffice.feature.organization.contract.management.ManagementState.Companion.updateCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationManagementViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase,
    private val updateOrganizationCategoryUseCase: UpdateOrganizationCategoryUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val fetchCategoriesUseCase: FetchCategoriesUseCase,
) : BaseViewModel<ManagementState, ManagementIntent, ManagementSideEffect>(
    initialState = ManagementState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)
    override fun handleIntent(intent: ManagementIntent) {
        when (intent) {
            is ManagementIntent.InitScreen -> {
                initScreen(intent.organizationInformation)
            }

            is ManagementIntent.NavigateToUp -> {
                navigateToUp()
            }

            is ManagementIntent.ClickCategoryItem -> {
                onClickCategoryItem(intent.index)
            }

            is ManagementIntent.ClickProfileImage -> {
                onClickProfileImage()
            }

            is ManagementIntent.ClickImageBottomSheetItem -> {
                onClickImageBottomSheetItem(intent.bottomSheetItem)
            }

            is ManagementIntent.HideImageBottomSheet -> {
                hideImageBottomSheet()
            }

            is ManagementIntent.PickImage -> {
                onPickImage(intent.uri)
            }

            is ManagementIntent.TakePicture -> {
                onTakePicture(intent.isUsed)
            }

            is ManagementIntent.ClickMemberManagementButton -> {
                onClickMemberManagementButton()
            }

            is ManagementIntent.ClickSaveButton -> {
                onClickSaveButton()
            }
        }
    }

    private fun initScreen(
        organizationInformation: OrganizationInformation
    ) = viewModelScope.launch {
        fetchCategoriesUseCase.invoke(Unit).onSuccess {
            val updatedCategories = it.map { item ->
                organizationInformation.category.find { it == item.title }?.let {
                    Category(item.id, item.title, true)
                } ?: item
            }
            reduce {
                copy(
                    organizationInformation = organizationInformation,
                    selectedImage = organizationInformation.profileImageUrl,
                    categoryList = updatedCategories,
                    isLoading = false
                )
            }
        }.onFailure {
            errorLogging(this.javaClass.name, "initScreen", it)
            showSnackBar(it.handleError())
        }.also {
            reduce { copy(isSaveLoading = false) }
        }
    }

    private fun onClickCategoryItem(index: Int) {
        reduce { updateCategoryItem(index) }
    }

    private fun onClickProfileImage() {
        showImageBottomSheet()
    }

    private fun onClickImageBottomSheetItem(item: ImageSelectionBottomSheetItem) {
        when (item) {
            ImageSelectionBottomSheetItem.GALLERY -> {
                postSideEffect { ManagementSideEffect.NavigateToGallery }
            }

            ImageSelectionBottomSheetItem.CAMERA -> {
                navigateToCamera()
            }

            ImageSelectionBottomSheetItem.DELETE -> {
                reduce { copy(selectedImage = "", isShowImageBottomSheet = false) }
            }
        }
        if (!currentState.isShowImageBottomSheet) return
        reduce { copy(isShowImageBottomSheet = false) }
    }

    private fun navigateToCamera() = viewModelScope.launch {
        getTakePictureUriUseCase.invoke(Unit)
            .onSuccess {
                takePictureUri.value = it
                postSideEffect { ManagementSideEffect.NavigateToCamera(it) }
            }
            .onFailure {
                errorLogging(this.javaClass.name, "navigateToCamera", it)
                showSnackBar(it.handleError())
            }
    }

    private fun onPickImage(uri: Uri?) {
        reduce { copy(selectedImage = (uri ?: "").toString()) }
    }

    private fun onTakePicture(isUsed: Boolean) {
        if (!isUsed) return
        reduce { copy(selectedImage = takePictureUri.value.toString()) }
    }

    private fun showImageBottomSheet() {
        reduce { copy(isShowImageBottomSheet = true) }
    }

    private fun hideImageBottomSheet() {
        reduce { copy(isShowImageBottomSheet = false) }
    }

    private fun navigateToUp() {
        postSideEffect { ManagementSideEffect.NavigateToUp }
    }

    private fun onClickMemberManagementButton() {
        postSideEffect { ManagementSideEffect.NavigateToMemberManagement(currentState.organizationInformation.id) }
    }

    private fun onClickSaveButton() = viewModelScope.launch {
        setIsSaveLoading(true)
        val imageDeferred = async {
            currentState.selectedImage.takeIf { it != currentState.organizationInformation.profileImageUrl }
                ?.let {
                    onSaveImage()
                }
        }
        val categoryDeferred = async { onSaveCategory() }
        imageDeferred.await()
        categoryDeferred.await()
        showSnackBar(R.string.organization_management_success_update_category)
        delay(300)
        navigateToUp()
        setIsSaveLoading(false)
    }

    private suspend fun onSaveCategory() {
        val category = currentState.categoryList.filter { it.isSelected }
        val originList = currentState.organizationInformation.category

        if (category.isEmpty()) {
            showSnackBar(R.string.organization_management_unselected_category)
            return
        }
        if (category.map { it.title }.toSet() == originList.toSet()) return

        val param = CategoryParam(
            organizationId = currentState.organizationInformation.id,
            categoryList = category.map { it.id }
        )
        updateOrganizationCategoryUseCase.invoke(param).onSuccess {

        }.onFailure {
            errorLogging(this.javaClass.name, "onSaveCategory", it)
            showSnackBar(it.handleError())
        }
    }

    private suspend fun onSaveImage(): String? {
        val param = ImageParam(
            uri = currentState.selectedImage.toUri(),
            purpose = ImagePurpose.ORGANIZATION_LOGO
        )
        return uploadImageUseCase.invoke(param).getOrElse {
            errorLogging(this.javaClass.name, "uploadImage", it)
            showSnackBar(it.handleError())
            null
        }
    }

    private fun showSnackBar(@StringRes stringId: Int) {
        postSideEffect {
            ManagementSideEffect.ShowSnackBar(stringId)
        }
    }

    private fun setIsSaveLoading(value: Boolean) {
        reduce { copy(isSaveLoading = value) }
    }
}