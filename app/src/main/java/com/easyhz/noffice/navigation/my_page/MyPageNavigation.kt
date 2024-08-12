package com.easyhz.noffice.navigation.my_page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.easyhz.noffice.feature.my_page.screen.MyPageScreen
import com.easyhz.noffice.feature.my_page.screen.detail.notice.NoticeScreen
import com.easyhz.noffice.feature.my_page.screen.detail.noticeDetail.NoticeDetailScreen
import com.easyhz.noffice.navigation.my_page.screen.MyPage
import com.easyhz.noffice.navigation.my_page.screen.Notice
import com.easyhz.noffice.navigation.my_page.screen.NoticeDetail

internal fun NavGraphBuilder.myPageGraph(
    navigateToUp: () -> Unit,
    navigateToNotice: () -> Unit,
    navigateToNoticeDetail: (com.easyhz.noffice.core.model.notice.Notice) -> Unit
) {
    composable<MyPage> {
        MyPageScreen(
            navigateToUp = navigateToUp,
            navigateToNotice = navigateToNotice
        )
    }

    composable<Notice> {
        NoticeScreen(
            navigateToUp = navigateToUp,
            navigateToNoticeDetail = navigateToNoticeDetail
        )
    }

    composable<NoticeDetail>(
        typeMap = NoticeDetail.typeMap
    ) {
        val args = it.toRoute<NoticeDetail>()
        NoticeDetailScreen(
            item = args.notice,
            navigateToUp = navigateToUp,
        )
    }
}

internal fun NavController.navigateToMyPage() {
    navigate(route = MyPage)
}

internal fun NavController.navigateToNotice() {
    navigate(route = Notice)
}

internal fun NavController.navigateToNoticeDetail(item: com.easyhz.noffice.core.model.notice.Notice) {
    navigate(route = NoticeDetail(item))
}