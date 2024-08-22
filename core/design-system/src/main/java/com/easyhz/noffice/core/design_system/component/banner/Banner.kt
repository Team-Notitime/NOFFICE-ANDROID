package com.easyhz.noffice.core.design_system.component.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Title1
import com.easyhz.noffice.core.design_system.theme.Title2
import com.easyhz.noffice.core.design_system.theme.White

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    userName: String,
    date: String,
) {
    val bannerIntro = stringResource(id = R.string.banner_intro)
    val bannerOutro = stringResource(id = R.string.banner_outro)
    val annotatedString = remember(userName, date, bannerIntro, bannerOutro) {
        if (userName.isBlank()) AnnotatedString("")
        else buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 32.sp)) {
                withStyle(style = Title1.toSpanStyle()) {
                    append(userName)
                }
                withStyle(style = Title2.toSpanStyle()) {
                    append(bannerIntro)
                }
                withStyle(style = Title1.toSpanStyle()) {
                    append(date)
                }
                withStyle(style = Title2.toSpanStyle()) {
                    append(bannerOutro)
                }
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 108.dp)
            .background(
                brush = Brush.verticalGradient(listOf(White, Green100)),
            )
    ) {
        Text(
            text = annotatedString,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .screenHorizonPadding()
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
fun SkeletonBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 108.dp)
            .background(
                brush = Brush.verticalGradient(listOf(White, Green100)),
            )
            .skeletonEffect()
    )
}

@Preview
@Composable
private fun BannerPrev() {
    Banner(
        userName = "푸바옹",
        date = "8월 27일 화요일"
    )
}