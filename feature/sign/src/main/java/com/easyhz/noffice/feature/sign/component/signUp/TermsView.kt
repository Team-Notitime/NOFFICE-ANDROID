package com.easyhz.noffice.feature.sign.component.signUp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey600
import com.easyhz.noffice.core.design_system.theme.SubBody12
import com.easyhz.noffice.core.design_system.theme.SubBody16
import com.easyhz.noffice.core.design_system.theme.SubTitle1
import com.easyhz.noffice.core.design_system.theme.Title1
import com.easyhz.noffice.feature.sign.contract.signUp.SignUpIntent
import com.easyhz.noffice.feature.sign.screen.signUp.SignUpViewModel
import com.easyhz.noffice.feature.sign.util.signUp.Terms
import java.util.EnumMap

@Composable
internal fun TermsView(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        Header(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 16.dp, bottom = 32.dp),
            title = stringResource(id = R.string.sign_up_terms_title),
            subTitle = stringResource(id = R.string.sign_up_terms_sub_title)
        )
        TermsCheck(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .weight(1f),
            isCheckedAll = uiState.isCheckedAllTerms,
            termsStatusMap = uiState.termsStatusMap,
            onClickDetail = { viewModel.postIntent(SignUpIntent.ClickTermsDetail(it)) },
            onClickCheck = { viewModel.postIntent(SignUpIntent.ClickTermsCheck(it)) }
        ) {
            viewModel.postIntent(SignUpIntent.ClickTermsAllCheck)
        }
        MediumButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.next_button),
            enabled = uiState.enabledStepButton[uiState.step.currentStep] ?: false
        ) {
            viewModel.postIntent(SignUpIntent.ClickNextButton)
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = Title1
        )
        Text(
            text = subTitle,
            style = SubTitle1,
            color = Grey600,
        )
    }
}

@Composable
private fun TermsCheck(
    modifier: Modifier = Modifier,
    isCheckedAll: Boolean,
    termsStatusMap: EnumMap<Terms, Boolean>,
    onClickDetail: (Terms) -> Unit,
    onClickCheck: (Terms) -> Unit,
    onClickAllCheck: () -> Unit
) {
    val allCheckIconId = if(isCheckedAll) R.drawable.ic_check_circle_on else R.drawable.ic_check_circle_off

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Box(modifier = Modifier.size(32.dp).noRippleClickable { onClickAllCheck() }) {
                Crossfade(
                    modifier = Modifier.align(Alignment.TopStart),
                    targetState = allCheckIconId,
                    label = "Check"
                ) { iconId ->
                    Image(
                        modifier = Modifier
                            .padding(top = 3.dp)
                            .size(18.dp),
                        painter = painterResource(id = iconId),
                        contentDescription = "check"
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_terms_all),
                    style = SubBody16,
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = stringResource(id = R.string.sign_up_terms_all_caption),
                    style = SubBody12,
                    color = Grey600,
                    textAlign = TextAlign.Justify
                )
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp), color = Grey100, thickness = 1.dp)
        Terms.entries.forEach { terms ->
            TermsItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                terms = terms,
                isChecked = termsStatusMap.getOrElse(terms, defaultValue = { false }),
                onClickDetail = { onClickDetail(terms) }
            ) {
                onClickCheck(terms)
            }
        }
    }
}

@Composable
private fun TermsItem(
    modifier: Modifier = Modifier,
    terms: Terms,
    isChecked: Boolean,
    onClickDetail: () -> Unit,
    onClickCheck: () -> Unit
) {
    val isRequiredString =
        if (terms.isRequired) R.string.sign_up_terms_required else R.string.sign_up_terms_not_required
    val checkedIconId = if(isChecked) R.drawable.ic_check_circle_on else R.drawable.ic_check_circle_off
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(32.dp).noRippleClickable { onClickCheck() }) {
            Crossfade(
                modifier = Modifier.align(Alignment.CenterStart),
                targetState = checkedIconId,
                label = "Check"
            ) { iconId ->
                Image(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = "check"
                )
            }
        }
        Row(
            modifier = Modifier.noRippleClickable { onClickDetail() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(bottom = 3.dp).align(Alignment.CenterVertically).weight(1f),
                text = stringResource(id = terms.stringId, stringResource(id = isRequiredString)),
                style = SubBody16,
                textAlign = TextAlign.Justify,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            if (terms.hasDetail) {
                Box(modifier = Modifier.size(32.dp)) {
                    Image(
                        modifier = Modifier.padding(bottom = 3.dp).size(24.dp).align(Alignment.CenterEnd),
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = "more"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun TermsViewPrev() {
    TermsView()
}