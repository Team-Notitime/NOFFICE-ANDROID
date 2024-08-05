package com.easyhz.noffice.feature.organization.component.creation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.FloatingBottomSheet
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.image.OrganizationCreationImageView
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.feature.organization.contract.creation.CreationIntent
import com.easyhz.noffice.feature.organization.screen.creation.OrganizationCreationViewModel
import com.easyhz.noffice.feature.organization.util.creation.BottomSheetItem
import com.easyhz.noffice.feature.organization.util.creation.CreationStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImageView(
    modifier: Modifier = Modifier,
    viewModel: OrganizationCreationViewModel = hiltViewModel(),
    creationStep: CreationStep
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {
        CommonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            creationStep = creationStep
        )
        Box(
            modifier = Modifier
                .padding(vertical = 28.dp)
                .size(280.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(24.dp))
                .background(Grey50)
                .noRippleClickable {
                    viewModel.postIntent(CreationIntent.ClickImageView)
                }
        ) {
            OrganizationCreationImageView(
                modifier = Modifier.size(280.dp),
                image = uiState.organizationImage
            ) {
                EmptyImageView(
                    modifier = Modifier.size(280.dp).align(Alignment.Center)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        MediumButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.next_button),
            enabled = uiState.enabledStepButton[uiState.step.currentStep] ?: false
        ) {
            viewModel.postIntent(CreationIntent.ClickNextButton)
        }
    }
    if (uiState.isShowImageBottomSheet) {
        FloatingBottomSheet(
            modifier = Modifier.padding(bottom = 32.dp),
            roundedCornerShape = RoundedCornerShape(24.dp),
            onDismissRequest = { viewModel.postIntent(CreationIntent.HideImageBottomSheet) }
        ) {
            BottomSheetItems(
                bottomSheetItem = enumValues<BottomSheetItem>().copyOfRange(
                    0,
                    if (uiState.organizationImage == Uri.EMPTY) 2 else 3
                ),
                onClick = { viewModel.postIntent(CreationIntent.ClickImageBottomSheetItem(it)) }
            )
        }
    }
}

@Composable
private fun EmptyImageView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(36.dp),
            painter = painterResource(id = R.drawable.ic_upload),
            contentDescription = "upload",
            tint = Grey300
        )
    }
}