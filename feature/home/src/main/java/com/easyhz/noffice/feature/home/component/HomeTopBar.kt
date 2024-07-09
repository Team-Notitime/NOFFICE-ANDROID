package com.easyhz.noffice.feature.home.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey800

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onClick: (HomeTopBarMenu) -> Unit
) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val selectedOffset by animateDpAsState(
        targetValue = 55.dp * selectedIndex.intValue,
        label = "selected"
    )
    Box(
        modifier = modifier
            .screenHorizonPadding()
            .fillMaxWidth()
            .height(44.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .height(3.dp)
                .width(50.dp)
                .align(Alignment.BottomStart)
                .offset(x = selectedOffset)
                .background(Green500)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            HomeTopBarMenu.entries.forEachIndexed { index, homeTopBarMenu ->
                Box(
                    modifier = modifier
                        .width(50.dp)
                        .noRippleClickable {
                            selectedIndex.intValue = index
                            onClick(homeTopBarMenu)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .width(50.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        text = homeTopBarMenu.label,
                        color = if (selectedIndex.intValue == index) {
                            Grey800
                        } else {
                            Grey400
                        },
                        fontSize = 22.sp
                    )
                }
            }

//            Text(text = "투두", modifier = Modifier.fillMaxHeight())
        }
        Icon(
            imageVector = Icons.Default.Adb,
            contentDescription = "",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

enum class HomeTopBarMenu(
    val label: String
) {
    NOTICE("노티"), TODO("투두")
}

@Preview
@Composable
private fun HomeTopBarPrev() {
    HomeTopBar() {

    }
}