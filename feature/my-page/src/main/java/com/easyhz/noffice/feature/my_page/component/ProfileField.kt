package com.easyhz.noffice.feature.my_page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.image.ProfileImage
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Body15
import com.easyhz.noffice.core.design_system.theme.Green600
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.SemiBold18
import com.easyhz.noffice.core.design_system.theme.White

@Composable
internal fun ProfileField(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    imageUrl: String,
    onChangeProfileImage: () -> Unit,
    onChangeName: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Profile(imageUrl = imageUrl, onClick = onChangeProfileImage)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .clickable { onChangeName() }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_menu_user_name, name),
                    style = SemiBold18,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "edit",
                    tint = Green600
                )
            }
            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = email,
                style = Body15,
                color = Grey500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun Profile(
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(modifier = modifier.size(84.dp).noRippleClickable { onClick() }) {
        ProfileImage(
            modifier = Modifier.size(76.dp).align(Alignment.TopStart),
            imageUrl = imageUrl
        )
        Box(
            modifier = Modifier
//                .padding(end = 4.dp, bottom = 4.dp)
                .size(32.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .background(Green600)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(18.dp),
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = "changeCamera",
                tint = White
            )
        }
    }
}