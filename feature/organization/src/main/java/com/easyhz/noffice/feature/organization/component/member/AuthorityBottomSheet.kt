package com.easyhz.noffice.feature.organization.component.member

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.BottomSheet
import com.easyhz.noffice.core.design_system.component.button.CircleCheck
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.SemiBold16
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.model.organization.member.MemberType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AuthorityBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    memberType: MemberType,
    onDismissRequest: () -> Unit,
    onClickAuthority: (MemberType) -> Unit,
    onClick: () -> Unit
) {
    BottomSheet(
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = White,
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MemberType.entries.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .clickable { onClickAuthority(item) }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.heightIn(min = 20.dp).weight(1f),
                        text = stringResource(id = item.subStringId),
                        style = SemiBold16,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (item == memberType) {
                        CircleCheck(isChecked = true, enabled = false)
                    }
                }
                if (index != MemberType.entries.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        thickness = 1.dp,
                        color = Grey100
                    )
                }
            }
            MediumButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                text = stringResource(id = R.string.organization_management_member_authority_button),
                onClick = onClick
            )
        }
    }
}