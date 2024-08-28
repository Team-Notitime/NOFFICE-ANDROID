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
import com.easyhz.noffice.core.model.image.ProfileImageParam
import com.easyhz.noffice.core.model.image.UpdateImageParam
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.core.model.organization.param.CategoryParam
import com.easyhz.noffice.domain.organization.usecase.category.FetchCategoriesUseCase
import com.easyhz.noffice.domain.organization.usecase.category.UpdateOrganizationCategoryUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UpdateImageUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UpdateOrganizationProfileImageUseCase
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
    private val updateImageUseCase: UpdateImageUseCase,
    private val updateOrganizationProfileImageUseCase: UpdateOrganizationProfileImageUseCase,
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
        fetchCategoriesUseCase.invoke(Unit).onSuccess { list ->
            val updatedCategories = list.map { item ->
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
        delay(300)
        setIsSaveLoading(false)
        navigateToUp()
        showSnackBar(R.string.organization_management_success_update_category)
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
        val imageUri = currentState.selectedImage?.toUri() ?: Uri.EMPTY
        val profileImageUrl = currentState.organizationInformation.profileImageUrl

        return if (profileImageUrl.isEmpty() || profileImageUrl == "null") {
            val param = ProfileImageParam(
                organizationId = currentState.organizationInformation.id,
                imageParam = ImageParam(
                    uri = imageUri,
                    purpose = ImagePurpose.ORGANIZATION_LOGO
                )
            )
            updateOrganizationProfileImage(param)
        } else {
            val param = UpdateImageParam(
                uri = imageUri,
                url = profileImageUrl,
                purpose = ImagePurpose.ORGANIZATION_LOGO
            )
            updateImage(param)
        }
    }

    private suspend fun updateOrganizationProfileImage(param: ProfileImageParam): String? {
        return updateOrganizationProfileImageUseCase.invoke(param).getOrElse {
            handleException(it, "updateOrganizationProfileImage")
        }
    }

    private suspend fun updateImage(param: UpdateImageParam): String? {
        return updateImageUseCase.invoke(param).getOrElse {
            handleException(it, "uploadImage")
        }
    }

    private fun handleException(exception: Throwable, methodName: String): String? {
        errorLogging(this.javaClass.name, methodName, exception)
        showSnackBar(exception.handleError())
        return null
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