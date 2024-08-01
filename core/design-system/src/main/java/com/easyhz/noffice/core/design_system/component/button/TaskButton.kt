package com.easyhz.noffice.core.design_system.component.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Blue100
import com.easyhz.noffice.core.design_system.theme.Blue600
import com.easyhz.noffice.core.design_system.theme.SemiBold14

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    text: String,
    isComplete: Boolean,
    maxLines: Int = Int.MAX_VALUE,
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .screenHorizonPadding()
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            )
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Check(isComplete = isComplete)
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(),
            text = text,
            style = SemiBold14.copy(
                textAlign = TextAlign.Unspecified,
                lineHeight = (14 * 1.5).sp
            ),
            color = Blue600,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
private fun Check(
    modifier: Modifier = Modifier,
    isComplete: Boolean
) {
    AnimatedContent(
        modifier = modifier,
        targetState = isComplete,
        label = "check"
    ) {
        when (it) {
            true -> {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_check_circle_blue),
                    contentDescription = "checkIcon",
                )
            }
            false -> {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Blue100)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaskButtonPrev() {
    TaskItem(
        text = "할",
        isComplete = false,
        onLongClick = null
    ) {

    }
}