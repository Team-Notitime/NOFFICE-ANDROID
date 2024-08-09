package com.easyhz.noffice.feature.my_page.contract

import android.net.Uri
import com.easyhz.noffice.core.common.base.UiSideEffect

sealed class MyPageSideEffect: UiSideEffect() {
    data object NavigateToUp: MyPageSideEffect()
    data object HideBottomSheet:  MyPageSideEffect()
    data object NavigateToGallery:  MyPageSideEffect()
    data class NavigateToCamera(val uri: Uri):  MyPageSideEffect()
}