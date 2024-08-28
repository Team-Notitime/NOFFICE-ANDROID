package com.easyhz.noffice.navigation.my_page

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.feature.my_page.screen.MyPageScreen
import com.easyhz.noffice.feature.my_page.screen.detail.consent.ConsentToInformationScreen
import com.easyhz.noffice.feature.my_page.screen.detail.notice.NoticeScreen
import com.easyhz.noffice.feature.my_page.screen.detail.noticeDetail.NoticeDetailScreen
import com.easyhz.noffice.feature.my_page.screen.detail.terms.TermsScreen
import com.easyhz.noffice.feature.my_page.screen.detail.withdrawal.WithdrawalScreen
import com.easyhz.noffice.navigation.my_page.screen.Consent
import com.easyhz.noffice.navigation.my_page.screen.MyPage
import com.easyhz.noffice.navigation.my_page.screen.Notice
import com.easyhz.noffice.navigation.my_page.screen.NoticeDetail
import com.easyhz.noffice.navigation.my_page.screen.Terms
import com.easyhz.noffice.navigation.my_page.screen.Withdrawal
import com.easyhz.noffice.transition.SlideDirection
import com.easyhz.noffice.transition.enterSlide
import com.easyhz.noffice.transition.exitSlide

internal fun NavGraphBuilder.myPageGraph(
    snackBarHostState: SnackbarHostState,
    navigateToUp: () -> Unit,
    navigateToTerms: (TermsType) -> Unit,
    navigateToNotice: () -> Unit,
    navigateToNoticeDetail: (com.easyhz.noffice.core.model.notice.Notice) -> Unit,
    navigateToConsent: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogIn: () -> Unit,
) {
    composable<MyPage> {
        MyPageScreen(
            snackBarHostState = snackBarHostState,
            navigateToUp = navigateToUp,
            navigateToTerms = navigateToTerms,
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

    composable<Terms>(
        typeMap = Terms.typeMap,
        enterTransition = { enterSlide(SlideDirection.Up) },
        exitTransition = { exitSlide(SlideDirection.Down) },
        popEnterTransition = { enterSlide(SlideDirection.Up) },
        popExitTransition = { exitSlide(SlideDirection.Down) }
    ) {
        val args = it.toRoute<Terms>()
        TermsScreen(
            termsType = args.termsType,
            navigateToUp = navigateToUp
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

internal fun NavController.navigateToTerms(termsType: TermsType) {
    navigate(route = Terms(termsType))
}