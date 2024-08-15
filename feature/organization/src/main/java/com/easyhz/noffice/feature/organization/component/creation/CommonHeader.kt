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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.SemiBold12
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.Title3
import com.easyhz.noffice.feature.organization.util.creation.CreationStep

@Composable
internal fun CommonHeader(
    modifier: Modifier = Modifier,
    creationStep: CreationStep
) {
    val (captionStringId, color) = remember {
        if (creationStep.isRequired) R.string.organization_creation_is_required to Green500
        else R.string.organization_creation_is_not_required to Grey400
    }
    Column(
        modifier = modifier.fillMaxWidth().padding(bottom = 8.dp),
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
        Text(text = stringResource(id = creationStep.titleId), style = Title3)
        Text(text = stringResource(id = captionStringId), style = SemiBold12, color = color)
    }
}

@Preview(showBackground = true)
@Composable
private fun CommonHeaderPrev() {
    CommonHeader(
        modifier = Modifier.padding(vertical = 16.dp),
        CreationStep.IMAGE
    )
}