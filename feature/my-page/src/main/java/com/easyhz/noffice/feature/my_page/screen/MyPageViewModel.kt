package com.easyhz.noffice.feature.my_page.screen

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.core.design_system.util.bottomSheet.ImageSelectionBottomSheetItem
import com.easyhz.noffice.domain.organization.usecase.image.GetTakePictureUriUseCase
import com.easyhz.noffice.feature.my_page.contract.MyPageIntent
import com.easyhz.noffice.feature.my_page.contract.MyPageSideEffect
import com.easyhz.noffice.feature.my_page.contract.MyPageState
import com.easyhz.noffice.feature.my_page.util.MyPageMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase
): BaseViewModel<MyPageState, MyPageIntent, MyPageSideEffect>(
    initialState = MyPageState.init()
) {
    private var takePictureUri = mutableStateOf(Uri.EMPTY)

    override fun handleIntent(intent: MyPageIntent) {
        when(intent) {
            is MyPageIntent.ClickBackButton -> { }
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
                reduce { copy(user = user.copy(profileImageUrl = "")) }
            }
        }
        if (!currentState.isShowImageBottomSheet) return
        hideImageBottomSheet()
    }

    private fun onPickImage(uri: Uri?) {
        reduce { copy(user = user.copy(profileImageUrl = (uri ?: "").toString())) }
    }

    private fun navigateToCamera() = viewModelScope.launch {
        getTakePictureUriUseCase.invoke(Unit)
            .onSuccess {
                takePictureUri.value = it
                postSideEffect { MyPageSideEffect.NavigateToCamera(it) }
            }
            .onFailure {
                // TODO fail 처리
                println("fail: $it")
            }
    }
    private fun onTakePicture(isUsed: Boolean) {
        if (!isUsed) return
        reduce { copy(user = user.copy(profileImageUrl = takePictureUri.value.toString())) }
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
}