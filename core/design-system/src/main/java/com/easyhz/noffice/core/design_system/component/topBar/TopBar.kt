package com.easyhz.noffice.core.design_system.component.topBar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.theme.semiBold
import com.easyhz.noffice.core.design_system.util.topBar.TopBarIconMenu
import com.easyhz.noffice.core.design_system.util.topBar.TopBarMenu

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun <T> HomeTopBar(
    modifier: Modifier = Modifier,
    tabs: Array<T>,
    onClickIconMenu: (TopBarIconMenu) -> Unit,
    onClickTab: (T) -> Unit
) where T : Enum<T>, T : TopBarMenu {
    val selectedIndex = rememberSaveable { mutableIntStateOf(0) }
    val width = if (tabs.size == 1) 88.dp else 105.dp

    Box(
        modifier = modifier
            .screenHorizonPadding()
            .fillMaxWidth()
            .height(44.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        TabRow(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = width)
                .align(Alignment.CenterStart),
            selectedTabIndex = selectedIndex.intValue,
            containerColor = Color.Transparent,
            contentColor = Grey400,
            divider = { },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .height(3.dp)
                        .tabIndicatorOffset(tabPositions[selectedIndex.intValue]),
                    color = Green500
                )
            }
        ) {
            tabs.forEachIndexed { index, topBarMenu ->
                Text(
                    modifier = Modifier
                        .padding(vertical = 8.5.dp)
                        .noRippleClickable {
                            selectedIndex.intValue = index
                            onClickTab(topBarMenu)
                        },
                    textAlign = TextAlign.Center,
                    text = topBarMenu.label,
                    color = if (selectedIndex.intValue == index) {
                        Grey800
                    } else {
                        Grey400
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = semiBold(22),
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            TopBarIconMenu.entries.forEach {
                Box(modifier = Modifier
                    .size(44.dp)
                    .noRippleClickable {
                        onClickIconMenu(it)
                    }) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp),
                        painter = painterResource(id = it.iconId),
                        contentDescription = it.label,
                        tint = Grey500
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun TopBarPrev() {
    HomeTopBar(
        modifier = Modifier.background(White),
        tabs = enumValues<MockTopBarMenus>(),
        onClickIconMenu = { }
    ) {

    }
}


enum class MockTopBarMenus : TopBarMenu {
    NOTICE {
        override val label: String
            get() = "메뉴"
    },
    TODO {
        override val label: String
            get() = "메뉴"
    },
}