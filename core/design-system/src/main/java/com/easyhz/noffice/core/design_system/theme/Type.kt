package com.easyhz.noffice.core.design_system.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.R

private val Pretendard = FontFamily(
    Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_extra_light, FontWeight.ExtraLight),
    Font(R.font.pretendard_thin, FontWeight.Thin),
)

private val LetterSpacing = 0.sp

val SemiBold14 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black,
    fontSize = 14.sp,
    lineHeight = 14.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)
fun semiBold(fontSize: Int) = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black,
    fontSize = fontSize.sp,
    lineHeight = fontSize.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Body14 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black,
    fontSize = 14.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Body15 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black,
    fontSize = 15.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubHeading15 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.Bold,
    color = Color.Black,
    fontSize = 15.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SemiBold16 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Color.Black,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)