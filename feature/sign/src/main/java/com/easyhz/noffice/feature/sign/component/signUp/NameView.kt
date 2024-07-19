package com.easyhz.noffice.feature.sign.component.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.textField.MainTextField
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Title1
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpIntent
import com.easyhz.noffice.feature.sign.screen.signUp.SignUpViewModel

@Composable
internal fun NameView(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 14.dp),
            text = stringResource(id = R.string.sign_up_name_title),
            style = Title1,
            lineHeight = 32.sp
        )
        MainTextField(
            modifier = Modifier.weight(0.3f),
            value = uiState.name,
            onValueChange = { viewModel.postIntent(SignUpIntent.ChangeNameTextValue(it)) },
            title = null,
            placeholder = stringResource(id = R.string.sign_up_name_placeholder),
            isFilled = false,
            singleLine = true,
            icon = null
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .noRippleClickable { viewModel.postIntent(SignUpIntent.ClearFocus) }
        )
        MediumButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.sign_up_button),
            enabled = uiState.enabledStepButton[uiState.step.currentStep] ?: false
        ) {
            viewModel.postIntent(SignUpIntent.ClickNextButton)
        }
    }
}