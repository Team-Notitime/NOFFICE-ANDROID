package com.easyhz.noffice.feature.announcement.screen.creation.promotion

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.common.util.collectInSideEffectWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey300
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.core.model.announcement.param.AnnouncementParam
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.component.creation.promotion.PromotionBottomSheet
import com.easyhz.noffice.feature.announcement.component.creation.promotion.PromotionCard
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.CardImage
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionIntent
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromotionScreen(
    modifier: Modifier = Modifier,
    viewModel: PromotionViewModel = hiltViewModel(),
    param: AnnouncementParam,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LaunchedEffect(key1 = Unit) {
        viewModel.postIntent(PromotionIntent.InitScreen(param))
    }
    NofficeBasicScaffold(
        modifier = Modifier.noRippleClickable { },
        bottomBar = {
            MediumButton(
                modifier = Modifier
                    .screenHorizonPadding()
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.announcement_creation_option_promotion_button),
                enabled = true
            ) {
                viewModel.postIntent(PromotionIntent.ClickBackButton)
            }
        },
        topBar = {
            DetailTopBar(
                leadingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = "left",
                            tint = Grey400
                        )
                    },
                    onClick = { }
                ),
                title = stringResource(id = R.string.announcement_creation_title),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreationTitle(
                modifier = Modifier.screenHorizonPadding(),
                title = stringResource(id = R.string.announcement_creation_option_promotion_title)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                LazyRow(
                    modifier = Modifier.padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(start = 16.dp, end = 44.dp)
                ) {
                    items(CardImage.entries) {
                        PromotionCard(
                            isSelected = uiState.selectCard == it,
                            cardImage = it
                        ) {
                            viewModel.postIntent(PromotionIntent.ClickPromotionCard(it))
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .height(52.dp)
                        .background(White)
                        .align(Alignment.CenterEnd)
                        .padding(horizontal = 4.dp)
                        .clickable { viewModel.postIntent(PromotionIntent.SetPromotionBottomSheet(true)) }
                        .padding(horizontal = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = "right",
                        tint = Grey300
                    )
                }
            }
            Crossfade(
                modifier = Modifier.padding(vertical = 12.dp),
                targetState = uiState.selectCard,
                label = "Check"
            ) { selectedCard ->
                Image(
                    modifier = Modifier
                        .screenHorizonPadding()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = selectedCard.imageId),
                    contentDescription = selectedCard.imageId.toString(),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        if(uiState.isShowPromotionBottomSheet) {
            PromotionBottomSheet(
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                selectedCard = uiState.bottomSheetSelectCard,
                hasPromotion = false,
                onDismissRequest = { viewModel.postIntent(PromotionIntent.HideUserNameBottomSheet) },
                onClickJoinPromotion = { /* FIXME */ },
                onClickItem = { viewModel.postIntent(PromotionIntent.ClickBottomSheetCard(it)) }
            ) { viewModel.postIntent(PromotionIntent.ClickBottomSheetSelectButton) }
        }
    }

    viewModel.sideEffect.collectInSideEffectWithLifecycle { sideEffect ->
        when(sideEffect) {
            is PromotionSideEffect.NavigateToUp -> { }
            is PromotionSideEffect.HidePromotionBottomSheet -> {
                sheetState.hide()
                viewModel.postIntent(PromotionIntent.SetPromotionBottomSheet(false))
            }
        }
    }
}