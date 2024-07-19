package com.easyhz.noffice.core.design_system.component.exception

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun ExceptionView(
    modifier: Modifier = Modifier,
    type: ExceptionType
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = type.resId),
            contentDescription = "no_organization"
        )
        Column(modifier = Modifier
            .padding(top = 10.dp)
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

@Preview
@Composable
private fun NoOrganizationPrev() {
    ExceptionView(
        modifier = Modifier.fillMaxSize(),
        type = ExceptionType.NO_ORGANIZATION
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