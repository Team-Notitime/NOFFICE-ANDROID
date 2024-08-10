package com.easyhz.noffice.feature.my_page.contract

import com.easyhz.noffice.core.common.base.UiState
import com.easyhz.noffice.core.model.profile.User

data class MyPageState(
    val user: User,
    val isCheckedNotification: Boolean,
    val isShowImageBottomSheet: Boolean,
    val isShowUserNameBottomSheet: Boolean,
    val userNameText: String,
): UiState() {
    companion object {
        fun init() = MyPageState(
            user = User(
                id = "",
                email = "guest@noffice.com",
                name = "푸바옹",
                profileImageUrl = ""
            ),
            isCheckedNotification = true,
            isShowImageBottomSheet = false,
            isShowUserNameBottomSheet = false,
            userNameText = ""
        )
    }
}
