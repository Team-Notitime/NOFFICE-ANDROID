package com.easyhz.noffice.navigation.my_page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.my_page.screen.MyPageScreen
import com.easyhz.noffice.feature.my_page.screen.detail.notice.NoticeScreen
import com.easyhz.noffice.navigation.my_page.screen.MyPage
import com.easyhz.noffice.navigation.my_page.screen.Notice

internal fun NavGraphBuilder.myPageGraph(
    navigateToUp: () -> Unit,
) {
    composable<MyPage> {
        MyPageScreen(
            navigateToUp = navigateToUp
        )
    }

    composable<Notice> {
        NoticeScreen(
            navigateToUp = navigateToUp,
            navigateToNoticeDetail = { }
        )
    }
}

internal fun NavController.navigateToMyPage() {
    navigate(route = MyPage)
}