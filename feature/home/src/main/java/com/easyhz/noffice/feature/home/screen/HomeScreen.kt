package com.easyhz.noffice.feature.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold
import com.easyhz.noffice.core.design_system.component.topBar.HomeTopBar
import com.easyhz.noffice.feature.home.util.HomeTopBarMenu

@Composable
fun HomeScreen() {
    val state = rememberSaveable {
        mutableStateOf(
            HomeTopBarMenu.NOTICE
        )
    }
    NofficeScaffold(
        topBar = {
            HomeTopBar(
                tabs = enumValues<HomeTopBarMenu>(),
                onClickIconMenu = { }
            ) {
                when(it) {
                    HomeTopBarMenu.NOTICE -> { state.value = HomeTopBarMenu.NOTICE }
                    HomeTopBarMenu.TODO -> { state.value = HomeTopBarMenu.TODO }
                }
            }
        }
    ) {
        when(state.value) {
            HomeTopBarMenu.NOTICE -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(it)) {

                Text(text = "노티", modifier = Modifier.align(Alignment.Center))
                }
            }
            HomeTopBarMenu.TODO -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Text(text = "투두", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
 //        Box(modifier = Modifier
//            .fillMaxSize()
//            .padding(it)) {
//            MediumButton(text = "안녕") {
//
//            }
//            Text(text = "home", modifier = Modifier.align(Alignment.Center))
//        }
    }
}