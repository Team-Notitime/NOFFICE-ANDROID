package com.easyhz.noffice.feature.my_page.contract

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.auth.UserInfo

data class MyPageState(
    val user: UserInfo,
    val selectedImage: String?,
    val isShowImageBottomSheet: Boolean,
    val isShowUserNameBottomSheet: Boolean,
    val userNameText: String,
): UiState() {
    companion object {
        fun init() = MyPageState(
            user = UserInfo(
                id = -1,
                profileImage = "",
                name = "",
                alias = "",
                organizations = emptyList()
            ),
            selectedImage = "",
            isShowImageBottomSheet = false,
            isShowUserNameBottomSheet = false,
            userNameText = ""
        )
    }
}
