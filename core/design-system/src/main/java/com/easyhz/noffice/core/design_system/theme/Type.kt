package com.easyhz.noffice.core.design_system.theme

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

val Title1 =  TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Grey800,
    fontSize = 24.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val Title2 =  TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.Normal,
    color = Grey800,
    fontSize = 24.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SemiBold14 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Grey800,
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
    color = Grey800,
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
    color = Grey800,
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
    color = Grey800,
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
    color = Grey800,
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
    color = Grey800,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubBody16 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.Medium,
    color = Grey800,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubBody14 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.Medium,
    color = Grey800,
    fontSize = 14.sp,
    lineHeight = 14.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val SubBody12 = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.Medium,
    color = Grey800,
    fontSize = 12.sp,
    lineHeight = 12.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val InputDialogTitle = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Grey800,
    fontSize = 20.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)

val CardExceptionTitle = TextStyle(
    fontFamily = Pretendard,
    fontWeight = FontWeight.SemiBold,
    color = Grey800,
    fontSize = 22.sp,
    letterSpacing = LetterSpacing,
    textAlign = TextAlign.Justify,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
)