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
            is MyPageIntent.ChangeUserName -> { }
            is MyPageIntent.ChangeProfileImage -> { onChangeProfileImage() }
            is MyPageIntent.ClickMenuItem -> { onClickMenuItem(intent.item) }
            is MyPageIntent.ClickImageBottomSheetItem -> { onClickImageBottomSheetItem(intent.item) }
            is MyPageIntent.PickImage -> { onPickImage(intent.uri) }
            is MyPageIntent.TakePicture -> { onTakePicture(intent.isUsed) }
            is MyPageIntent.HideImageBottomSheet -> { hideBottomSheet() }
            is MyPageIntent.CompleteHideBottomSheet -> { completeHideBottomSheet() }
        }
    }

    private fun onChangeProfileImage() {
        reduce { copy(isShowImageBottomSheet = true) }
    }

    private fun onClickMenuItem(item: MyPageMenu) {
        when(item) {
            MyPageMenu.NOTIFICATION -> {
                reduce { copy(isCheckedNotification = !currentState.isCheckedNotification) }
            }
            else -> {

            }
        }
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
        hideBottomSheet()
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

    private fun hideBottomSheet() {
        postSideEffect { MyPageSideEffect.HideBottomSheet }
    }

    private fun completeHideBottomSheet() {
        reduce { copy(isShowImageBottomSheet = false) }
    }
}