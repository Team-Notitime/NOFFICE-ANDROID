package com.easyhz.noffice.feature.sign.component.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.extension.buttonShadowEffect
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.util.interaction.useInteraction
import com.easyhz.noffice.feature.sign.util.login.SocialLoginType


@Composable
internal fun LoginView(
    modifier: Modifier = Modifier,
    onClickSocial: SocialLoginType.OnItemClickListener,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SocialLoginButton(
            socialLoginType = SocialLoginType.GOOGLE,
            onClick = onClickSocial::onClickGoogle
        )
    }
}

@Composable
private fun SocialLoginButton(
    modifier: Modifier = Modifier,
    socialLoginType: SocialLoginType,
    onClick: () -> Unit
) {
    val (interactionSource, scale) = useInteraction()
    Row(
        modifier = modifier
            .scale(scale)
            .buttonShadowEffect(
                shadowColor = Color.Black.copy(0.25f),
                borderRadius = 12.dp,
                blurRadius = 2.dp,
                offsetY = 1.dp
            )
            .height(52.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(socialLoginType.containerColor)
            .noRippleClickable(interactionSource) { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = socialLoginType.iconId),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = socialLoginType.stringId),
            style = SubBody14,
            color = socialLoginType.contentColor
        )
    }
}