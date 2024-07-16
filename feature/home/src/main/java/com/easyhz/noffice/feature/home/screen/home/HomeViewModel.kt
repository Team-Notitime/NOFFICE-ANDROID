package com.easyhz.noffice.feature.home.screen.home

import com.easyhz.noffice.core.common.base.BaseViewModel
import com.easyhz.noffice.feature.home.contract.home.HomeIntent
import com.easyhz.noffice.feature.home.contract.home.HomeSideEffect
import com.easyhz.noffice.feature.home.contract.home.HomeState
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeState, HomeIntent, HomeSideEffect>(
    initialState = HomeState.init()
) {
    override fun handleIntent(intent: HomeIntent) {
        when(intent) {
          is HomeIntent.ChangeTopBarMenu ->  { onChangeTopBarMenu(intent.topBarMenu) }
        }
    }


    private fun onChangeTopBarMenu(topBarMenu: HomeTopBarMenu) {
        reduce { copy(topBarMenu = topBarMenu) }
    }
}