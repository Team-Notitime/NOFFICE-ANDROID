package com.easyhz.noffice.core.design_system.component.exception

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.theme.CardExceptionSubTitle
import com.easyhz.noffice.core.design_system.theme.CardExceptionTitle
import com.easyhz.noffice.core.design_system.util.exception.ExceptionType

@Composable
internal fun ExceptionView(
    modifier: Modifier = Modifier,
    type: ExceptionType
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = type.resId),
                contentDescription = "no_group"
            )
            Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = type.titleStringId),
                    textAlign = TextAlign.Center,
                    style = CardExceptionTitle,
                )
                Text(
                    modifier = Modifier,
                    text = stringResource(id = type.subTitleStringId),
                    textAlign = TextAlign.Center,
                    style = CardExceptionSubTitle,
                )
            }
        }
    }
}

@Preview
@Composable
private fun NoGroupPrev() {
    ExceptionView(
        modifier = Modifier.fillMaxSize(),
        type = ExceptionType.NO_GROUP
    )
}

@Preview
@Composable
private fun NoTaskPrev() {
    ExceptionView(
        modifier = Modifier.fillMaxSize(),
        type = ExceptionType.NO_TASK
    )
}