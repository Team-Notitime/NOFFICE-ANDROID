package com.easyhz.noffice.core.design_system.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Blue100
 import com.easyhz.noffice.core.design_system.theme.Blue700
import com.easyhz.noffice.core.design_system.theme.Body14
import com.easyhz.noffice.core.design_system.theme.Green100
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Green700
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.Grey600


@Composable
fun CheckButton(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    text: String,
    isComplete: Boolean,
    color: CheckButtonDefaults = CheckButtonDefaults.default(),
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .noRippleClickable {
                onClick()
            }
            .heightIn(min = 42.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isComplete) color.completeContainerColor else color.incompleteContainerColor)
            .padding(vertical = 14.dp)
            .screenHorizonPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            color = if(isComplete) color.completeContentColor else color.incompleteContentColor,
            style = Body14,
            textAlign = textAlign
        )
        Box(modifier = Modifier.heightIn(18.dp)) {
            if ((isComplete && color.completeIconColor != null) || color.incompleteIconColor != null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "check",
                    tint = (if (isComplete) color.completeIconColor else color.incompleteIconColor) ?: color.completeContentColor
                )
            }
        }
    }
}



@Immutable
data class CheckButtonDefaults(
    val completeContainerColor: Color,
    val completeContentColor: Color,
    val completeIconColor: Color?,
    val incompleteContainerColor: Color,
    val incompleteContentColor: Color,
    val incompleteIconColor: Color?,
) {
    companion object {
        fun default() = CheckButtonDefaults(
            completeContainerColor = Grey100,
            completeContentColor = Grey400,
            completeIconColor = Grey300,
            incompleteContainerColor = Blue100,
            incompleteContentColor = Blue700,
            incompleteIconColor = null
        )
    }
}

@Preview(group = "checkButton", name = "short - incomplete")
@Composable
private fun CheckButtonShortIncompletePrev() {
    CheckButton(
        modifier = Modifier.fillMaxWidth(),
        text = "할 일",
        isComplete = false,
    ) {

    }

}

@Preview(group = "checkButton", name = "short - complete")
@Composable
private fun CheckButtonShortCompletePrev() {
    CheckButton(
        modifier = Modifier.fillMaxWidth(),
        text = "할 일",
        isComplete = true,
    ) {

    }
}

@Preview(group = "checkButton", name = "Long - incomplete")
@Composable
private fun CheckButtonLongIncompletePrev() {
    CheckButton(
        modifier = Modifier.fillMaxWidth(),
        text = "텍스트가 길어지마어리ㅏ너무아ㅣ룬이ㅏ무라ㅣㅜㅇㄴ피ㅏㅜㅇㄹ니ㅏ퓌ㅏㅇ루파ㅣㅜㅇ리ㅏ퓌ㅏ움리ㅏㅜㄴ이ㅏ루니ㅏ울",
        isComplete = false,
    ) {

    }
}

@Preview(group = "checkButton", name = "Long - complete")
@Composable
private fun CheckButtonLongCompletePrev() {
    CheckButton(
        modifier = Modifier.fillMaxWidth(),
        text = "텍스트가 길어지마어리ㅏ너무아ㅣ룬이ㅏ무라ㅣㅜㅇㄴ피ㅏㅜㅇㄹ니ㅏ퓌ㅏㅇ루파ㅣㅜㅇ리ㅏ퓌ㅏ움리ㅏㅜㄴ이ㅏ루니ㅏ울",
        isComplete = true,
    ) {

    }
}

@Preview(group = "checkButton", name = "organization - incomplete")
@Composable
private fun CheckButtonOrganizationIncompletePrev() {
    CheckButton(
        modifier = Modifier.width(300.dp),
        text = "CMC 15th",
        isComplete = true,
        color = CheckButtonDefaults(
            completeContainerColor = Green100,
            completeContentColor = Green700,
            completeIconColor = Green700,
            incompleteContainerColor = Grey50,
            incompleteContentColor = Grey600,
            incompleteIconColor = Grey300
        )
    ) {

    }

}

@Preview(group = "checkButton", name = "organization - complete")
@Composable
private fun CheckButtonOrganizationCompletePrev() {
    CheckButton(
        modifier = Modifier.width(300.dp),
        text = "CMC 15th",
        isComplete = false,
        color = CheckButtonDefaults(
            completeContainerColor = Green100,
            completeContentColor = Green500,
            completeIconColor = Green500,
            incompleteContainerColor = Grey50,
            incompleteContentColor = Grey600,
            incompleteIconColor = Grey300
        )
    ) {

    }
}


@Preview(group = "checkButton", name = "organization - complete")
@Composable
private fun CheckButtonOrganizationCompleteNullPrev() {
    CheckButton(
        modifier = Modifier.width(300.dp),
        text = "CMC 15th",
        isComplete = true,
        color = CheckButtonDefaults(
            completeContainerColor = Green100,
            completeContentColor = Green700,
            completeIconColor = null,
            incompleteContainerColor = Grey50,
            incompleteContentColor = Grey600,
            incompleteIconColor = null
        )
    ) {

    }
}


