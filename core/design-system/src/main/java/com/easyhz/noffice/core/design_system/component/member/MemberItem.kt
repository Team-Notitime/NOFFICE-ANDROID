package com.easyhz.noffice.core.design_system.component.member

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.CircleCheck
import com.easyhz.noffice.core.design_system.component.image.ProfileImage
import com.easyhz.noffice.core.design_system.extension.skeletonEffect
import com.easyhz.noffice.core.design_system.theme.SemiBold14
import com.easyhz.noffice.core.model.common.Member
import com.easyhz.noffice.core.model.organization.member.MemberType

@Composable
fun MemberItem(
    modifier: Modifier = Modifier,
    member: Member,
    isEditMode: Boolean,
    onClick: () -> Unit = { }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = isEditMode) { onClick() }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(
            modifier = Modifier.size(36.dp),
            imageUrl = member.profileImage
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = member.alias, style = SemiBold14)
            if (member.role == MemberType.LEADER) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_crown),
                    contentDescription = "leader"
                )
            }
        }
        AnimatedVisibility(
            visible = isEditMode,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircleCheck(
                isChecked = member.isSelected,
                enabled = false
            )
        }
    }
}

@Composable
fun MemberItemSkeleton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .skeletonEffect(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .skeletonEffect(),
        )
        Box(modifier = Modifier
            .height(16.dp)
            .fillMaxWidth(0.3f)
            .skeletonEffect())
    }
}


@Preview(showBackground = true)
@Composable
private fun MemberItemPrev() {
    MemberItem(
        member = Member(
            id = 1,
            alias = "김철수",
            profileImage = "https://randomuser.me/api",
            role = MemberType.LEADER,
            isSelected = false,
            name = "김철수"
        ),
        isEditMode = true
    )
}