package com.easyhz.noffice.feature.announcement.screen.creation.promotion

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.button.MediumButton
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.extension.noRippleClickable
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu
import com.easyhz.noffice.feature.announcement.component.creation.CreationTitle
import com.easyhz.noffice.feature.announcement.component.creation.promotion.PromotionCard
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.CardImage
import com.easyhz.noffice.feature.announcement.contract.creation.promotion.PromotionIntent

@Composable
fun PromotionScreen(
    modifier: Modifier = Modifier,
    viewModel: PromotionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                    onClick = {  }
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
            LazyRow(
                modifier = Modifier.padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
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
    }
}