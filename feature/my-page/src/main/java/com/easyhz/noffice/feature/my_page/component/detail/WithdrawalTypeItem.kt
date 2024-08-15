package com.easyhz.noffice.feature.my_page.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.feature.my_page.util.WithdrawalType

@Composable
internal fun WithdrawalTypeItem(
    modifier: Modifier = Modifier,
    type: WithdrawalType
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dot),
            contentDescription = "dot",
            tint = Grey600
        )
        Text(text = stringResource(id = type.stringId), color = Grey600, style = SubBody14)
    }
}

@Preview
@Composable
private fun prev() {
    WithdrawalTypeItem(type = WithdrawalType.ID)
}