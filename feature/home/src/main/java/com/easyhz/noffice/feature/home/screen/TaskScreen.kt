package com.easyhz.noffice.feature.home.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TaskScreen(sharedTransitionScope: SharedTransitionScope,
               animatedVisibilityScope: AnimatedVisibilityScope
) {
    NofficeScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Home")
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            Text(text = "home", modifier = Modifier.align(Alignment.Center))
            with(sharedTransitionScope) {
                Text(text = "노티 내용을 입력해 주세요", modifier = Modifier.sharedElement(
                    rememberSharedContentState(key = "image"),
                    animatedVisibilityScope = animatedVisibilityScope
                ))
            }
        }
    }
}