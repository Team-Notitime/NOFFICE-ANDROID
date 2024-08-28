package com.easyhz.noffice.feature.my_page.screen

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.common.error.handleError
import com.easyhz.noffice.core.common.util.errorLogging
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.core.model.image.ImageParam
import com.easyhz.noffice.core.model.image.ImagePurpose
import com.easyhz.noffice.core.model.image.UpdateImageParam
import com.easyhz.noffice.domain.my_page.usecase.FetchUserInfoUseCase
import com.easyhz.noffice.domain.my_page.usecase.UpdateMemberProfileImageUseCase
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.domain.organization.usecase.image.UpdateImageUseCase
import com.easyhz.noffice.feature.my_page.contract.MyPageIntent
import com.easyhz.noffice.feature.my_page.contract.MyPageSideEffect
import com.easyhz.noffice.feature.my_page.contract.MyPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val updateMemberProfileImageUseCase: UpdateMemberProfileImageUseCase,
    private val updateImageUseCase: UpdateImageUseCase,
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase,
): BaseViewModel<MyPageState, MyPageIntent, MyPageSideEffect>(
    initialState = MyPageState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)

    override fun handleIntent(intent: MyPageIntent) {
        when(intent) {
            is MyPageIntent.ClickBackButton -> { navigateToUp() }
            is MyPageIntent.ClickUserName -> { onClickUserName() }
            is MyPageIntent.ChangeProfileImage -> { onChangeProfileImage() }
            is MyPageIntent.ClickImageBottomSheetItem -> { onClickImageBottomSheetItem(intent.item) }
            is MyPageIntent.PickImage -> { onPickImage(intent.uri) }
            is MyPageIntent.TakePicture -> { onTakePicture(intent.isUsed) }
            is MyPageIntent.HideImageBottomSheet -> { hideImageBottomSheet() }
            is MyPageIntent.CompleteHideBottomSheet -> { completeHideBottomSheet() }
            is MyPageIntent.HideUserNameBottomSheet -> { hideUserNameBottomSheet() }
            is MyPageIntent.ChangeUserNameText -> { onChangeUserNameText(intent.text) }
            is MyPageIntent.SaveUserName -> { saveUserName() }
            is MyPageIntent.ClearUserNameText -> { clearUserNameText() }
        }
    }

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() = viewModelScope.launch {
        fetchUserInfoUseCase.invoke(Unit).onSuccess {
            reduce { copy(user = it, selectedImage = it.profileImage) }
        }.onFailure {
            errorLogging(this.javaClass.simpleName, "fetchUserInfo", it)
            showSnackBar(it.handleError())
        }

    }

    private fun onChangeProfileImage() {
        reduce { copy(isShowImageBottomSheet = true) }
    }

    private fun onClickImageBottomSheetItem(item: ImageSelectionBottomSheetItem) {
        when (item) {
            ImageSelectionBottomSheetItem.GALLERY -> {
                postSideEffect { MyPageSideEffect.NavigateToGallery }
            }

            ImageSelectionBottomSheetItem.CAMERA -> {
                navigateToCamera()
            }

            ImageSelectionBottomSheetItem.DELETE -> {
                reduce { copy(selectedImage = "") }
            }
        }
        if (!currentState.isShowImageBottomSheet) return
        hideImageBottomSheet()
    }

    private fun onPickImage(uri: Uri?) {
        reduce { copy(selectedImage =(uri ?: "").toString()) }
        updateProfileImage()
    }

    private fun navigateToCamera() = viewModelScope.launch {
        getTakePictureUriUseCase.invoke(Unit)
            .onSuccess {
                takePictureUri.value = it
                postSideEffect { MyPageSideEffect.NavigateToCamera(it) }
            }
            .onFailure {
                errorLogging(this.javaClass.simpleName, "navigateToCamera", it)
                showSnackBar(it.handleError())
            }
    }
    private fun onTakePicture(isUsed: Boolean) {
        if (!isUsed) return
        reduce { copy(selectedImage = takePictureUri.value.toString()) }
        updateProfileImage()
    }

    private fun hideImageBottomSheet() {
        postSideEffect { MyPageSideEffect.HideBottomSheet }
    }

    private fun completeHideBottomSheet() {
        reduce { copy(isShowImageBottomSheet = false) }
    }

    private fun onClickUserName() {
        reduce { copy(isShowUserNameBottomSheet = true) }
        viewModelScope.launch {
            delay(300)
            postSideEffect { MyPageSideEffect.RequestFocus }
        }
    }

    private fun hideUserNameBottomSheet() {
        reduce { copy(isShowUserNameBottomSheet = false) }
    }

    private fun onChangeUserNameText(newText: String) {
        reduce { copy(userNameText = newText) }
    }

    private fun saveUserName() {
        // TODO 저장 로직
        if (currentState.userNameText.isBlank()) return
        reduce { copy(user = user.copy(name = userNameText), userNameText = "") }
        hideUserNameBottomSheet()
    }

    private fun clearUserNameText() {
        reduce { copy(userNameText = "") }
    }

    private fun navigateToUp() {
        postSideEffect { MyPageSideEffect.NavigateToUp }
    }

    private fun showSnackBar(stringId: Int) {
        postSideEffect { MyPageSideEffect.ShowSnackBar(stringId) }
    }

    private fun updateProfileImage() = viewModelScope.launch {
        if(currentState.selectedImage.isNullOrBlank()) {
            // delete
        } else {
            onSaveImage(currentState.selectedImage?.toUri() ?: Uri.EMPTY)
        }
    }

    private suspend fun onSaveImage(imageUri: Uri): String? {
        val profileImageUrl = currentState.user.profileImage

        return if (profileImageUrl.isNullOrBlank() || profileImageUrl == "null") {
            val param = ImageParam(
                    uri = imageUri,
                    purpose = ImagePurpose.MEMBER_PROFILE
            )
            updateMemberProfileImage(param)
        } else {
            val param = UpdateImageParam(
                uri = imageUri,
                url = profileImageUrl,
                purpose = ImagePurpose.MEMBER_PROFILE
            )
            updateImage(param)
        }
    }
    private suspend fun updateMemberProfileImage(param: ImageParam): String? {
        return updateMemberProfileImageUseCase.invoke(param).getOrElse {
            handleException(it, "updateMemberProfileImage")
        }
    }
//
    private suspend fun updateImage(param: UpdateImageParam): String? {
        return updateImageUseCase.invoke(param).getOrElse {
            handleException(it, "updateImage")
        }
    }


    private fun handleException(exception: Throwable, methodName: String): String? {
        errorLogging(this.javaClass.name, methodName, exception)
        showSnackBar(exception.handleError())
        return null
    }

}