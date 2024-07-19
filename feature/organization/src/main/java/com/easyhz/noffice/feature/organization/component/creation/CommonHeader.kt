package com.easyhz.noffice.feature.organization.component.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.Title3

@Composable
internal fun CommonHeader(
    modifier: Modifier = Modifier,
    title: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_grid),
                contentDescription = "grid",
                tint = Green500
            )
            Text(
                text = stringResource(id = R.string.organization_creation_title),
                style = SemiBold16,
                color = Green500
            )
        }
        Text(text = title, style = Title3)
    }
}

@Preview(showBackground = true)
@Composable
private fun CommonHeaderPrev() {
    CommonHeader(
        modifier = Modifier.padding(vertical = 16.dp),
        title = "그룹 이름 머임?"
    )
}