package com.easyhz.noffice.feature.organization.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.loading.LoadingIndicator
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.feature.organization.util.creation.PromotionCaptionType

@Composable
internal fun PromotionCaption(
    isLoading: Boolean,
    isValid: Boolean
) {
    AnimatedContent(
        targetState = when {
            isLoading -> PromotionCaptionType.LOADING
            isValid -> PromotionCaptionType.VALID
            else -> PromotionCaptionType.INVALID
        },
        transitionSpec = {
            fadeIn() togetherWith fadeOut() using SizeTransform(clip = false)
        }, label = "promotionCaption"
    ) { targetState ->
        Caption(type = targetState)
    }
}

@Composable
private fun Caption(
    type: PromotionCaptionType
) {
    Row(
        modifier = Modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = type.horizontalArrangement
    ) {
        type.iconId?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = it),
                contentDescription = type.name,
                tint = type.color
            )
        } ?: LoadingIndicator(
            modifier = Modifier.size(24.dp),
            isLoading = true
        )

        Text(text = stringResource(id = type.titleId), style = SubBody12, color = type.color)
    }
}