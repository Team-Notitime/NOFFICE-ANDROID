package com.easyhz.noffice.core.design_system.provider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import com.easyhz.noffice.core.design_system.theme.NoRippleTheme
import com.easyhz.noffice.core.design_system.util.interaction.NofficeIndicationNodeFactory

@Composable
fun NofficeLocalProvider(
    type: ProviderType = ProviderType.BASIC,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        *type.values
    ) {
        content()
    }
}

enum class ProviderType(vararg val values: ProvidedValue<*>) {
    @OptIn(ExperimentalFoundationApi::class)
    BASIC(
        LocalOverscrollConfiguration provides null,
        LocalRippleTheme provides NoRippleTheme,
        LocalIndication provides NofficeIndicationNodeFactory,
    )
}