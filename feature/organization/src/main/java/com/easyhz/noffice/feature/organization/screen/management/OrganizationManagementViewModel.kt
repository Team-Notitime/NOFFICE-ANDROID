package com.easyhz.noffice.feature.organization.screen.management

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.model.organization.OrganizationInformation
import com.easyhz.noffice.core.model.organization.category.Category
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.feature.organization.contract.detail.MemberType
import com.easyhz.noffice.feature.organization.contract.management.ManagementIntent
import com.easyhz.noffice.feature.organization.contract.management.ManagementSideEffect
import com.easyhz.noffice.feature.organization.contract.management.ManagementState
import com.easyhz.noffice.feature.organization.contract.management.ManagementState.Companion.updateCategoryItem
import com.easyhz.noffice.feature.organization.util.creation.BottomSheetItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationManagementViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase
) : BaseViewModel<ManagementState, ManagementIntent, ManagementSideEffect>(
    initialState = ManagementState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)
    override fun handleIntent(intent: ManagementIntent) {
        when (intent) {
            is ManagementIntent.InitScreen -> {
                initScreen(intent.organizationInformation, intent.numberOfMembers)
            }
            is ManagementIntent.NavigateToUp -> {}
            is ManagementIntent.ClickCategoryItem -> { onClickCategoryItem(intent.index) }
            is ManagementIntent.ClickProfileImage -> { onClickProfileImage() }
            is ManagementIntent.ClickImageBottomSheetItem -> { onClickImageBottomSheetItem(intent.bottomSheetItem) }
            is ManagementIntent.HideImageBottomSheet -> { hideImageBottomSheet() }
            is ManagementIntent.PickImage -> { onPickImage(intent.uri) }
            is ManagementIntent.TakePicture -> { onTakePicture(intent.isUsed) }
        }
    }

    private fun initScreen(
        organizationInformation: OrganizationInformation,
        numberOfMembers: LinkedHashMap<MemberType, Int>
    ) {
        val categoryList = currentState.category.map { item ->
            Category(
                title = item.title,
                isSelected = organizationInformation.category.any { it == item.title }
            )
        }
        reduce {
            copy(
                organizationInformation = organizationInformation,
                category = categoryList,
                numberOfMembers = numberOfMembers,
                selectedImage = organizationInformation.profileImageUrl,
                isLoading = false
            )
        }
    }

    private fun onClickCategoryItem(index: Int) {
        reduce { updateCategoryItem(index) }
    }
    private fun onClickProfileImage() {
        showImageBottomSheet()
    }
    private fun onClickImageBottomSheetItem(item: BottomSheetItem) {
        when (item) {
            BottomSheetItem.GALLERY -> {
                postSideEffect { ManagementSideEffect.NavigateToGallery }
            }

            BottomSheetItem.CAMERA -> {
                navigateToCamera()
            }

            BottomSheetItem.DELETE -> {
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
                // TODO fail 처리
                println("fail: $it")
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

}