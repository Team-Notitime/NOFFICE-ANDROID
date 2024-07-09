package com.easyhz.noffice.navigation.my_page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.easyhz.noffice.feature.my_page.MyPageScreen
import com.easyhz.noffice.navigation.my_page.screen.MyPage

internal fun NavGraphBuilder.myPageScreen() {
    composable<MyPage> {
        MyPageScreen()
    }
}

internal fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(
        route = MyPage,
        navOptions = navOptions
    )
}