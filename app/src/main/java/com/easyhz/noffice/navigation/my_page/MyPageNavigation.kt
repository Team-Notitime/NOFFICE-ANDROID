package com.easyhz.noffice.navigation.my_page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.easyhz.noffice.feature.my_page.screen.MyPageScreen
import com.easyhz.noffice.feature.my_page.screen.detail.consent.ConsentToInformationScreen
import com.easyhz.noffice.feature.my_page.screen.detail.notice.NoticeScreen
import com.easyhz.noffice.feature.my_page.screen.detail.noticeDetail.NoticeDetailScreen
import com.easyhz.noffice.feature.my_page.screen.detail.withdrawal.WithdrawalScreen
import com.easyhz.noffice.navigation.my_page.screen.Consent
import com.easyhz.noffice.navigation.my_page.screen.MyPage
import com.easyhz.noffice.navigation.my_page.screen.Notice
import com.easyhz.noffice.navigation.my_page.screen.NoticeDetail
import com.easyhz.noffice.navigation.my_page.screen.Withdrawal

internal fun NavGraphBuilder.myPageGraph(
    navigateToUp: () -> Unit,
    navigateToNotice: () -> Unit,
    navigateToNoticeDetail: (com.easyhz.noffice.core.model.notice.Notice) -> Unit,
    navigateToConsent: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogIn: () -> Unit,
) {
    composable<MyPage> {
        MyPageScreen(
            navigateToUp = navigateToUp,
            navigateToNotice = navigateToNotice,
            navigateToConsent = navigateToConsent,
            navigateToWithdrawal = navigateToWithdrawal,
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

    composable<Consent> {
        ConsentToInformationScreen(
            navigateToUp = navigateToUp
        )
    }

    composable<Withdrawal> {
        WithdrawalScreen(
            navigateToUp = navigateToUp,
            navigateToLogIn = navigateToLogIn
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

internal fun NavController.navigateToConsent() {
    navigate(route = Consent)
}

internal fun NavController.navigateToWithdrawal() {
    navigate(route = Withdrawal)
}