package com.easyhz.noffice.feature.announcement.component.creation.place

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.image.OpenGraphImage
import com.easyhz.noffice.core.design_system.extension.screenHorizonPadding
import com.easyhz.noffice.core.design_system.theme.Footer14
import com.easyhz.noffice.core.design_system.theme.Grey100
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey500
import com.easyhz.noffice.core.design_system.theme.Grey800
import com.easyhz.noffice.core.design_system.theme.SubBody14
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.theme.Green500
import com.easyhz.noffice.core.model.announcement.creation.place.OpenGraph

@Composable
internal fun OpenGraphCard(
    modifier: Modifier = Modifier,
    openGraph: OpenGraph,
    isLoading: Boolean,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.widthIn(min = 30.dp))
        Column(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = Grey100,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            AnimatedContent(
                targetState = isLoading,
                label = "openGraph"
            ) {
                if (it) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 3.dp,
                            color = Green500
                        )
                    }
                } else {
                    Column {
                        OpenGraphImage(
                            modifier = Modifier
                                .weight(1f),
                            image = openGraph.imageUrl,
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Grey100,
                                    shape = RoundedCornerShape(
                                        bottomStart = 12.dp,
                                        bottomEnd = 12.dp
                                    )
                                )
                                .screenHorizonPadding()
                                .padding(vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            OpenGraphText(
                                text = openGraph.title,
                                style = SubBody14,
                                color = Grey800,
                            )
                            OpenGraphText(
                                text = openGraph.description,
                                defaultTextId = R.string.announcement_creation_option_place_open_graph_error_caption,
                                style = SubBody14,
                                color = Grey500,
                            )
                            OpenGraphText(
                                modifier = Modifier.padding(top = 8.dp),
                                text = openGraph.url,
                                style = Footer14,
                                color = Grey400,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OpenGraphText(
    modifier: Modifier = Modifier,
    text: String?,
    defaultTextId: Int = R.string.announcement_creation_option_place_open_graph_error,
    style: TextStyle,
    color: Color
) {
    Text(
        modifier = modifier,
        text = text.takeIf { !it.isNullOrBlank() } ?: stringResource(id = defaultTextId),
        style = style,
        color = color,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}


@Preview
@Composable
private fun OpenGraphCardPrev() {
    val openGraph = OpenGraph(
        title = "네이버",
        description = "네이버 메인에서 다양한 정보와 유용한 컨텐츠를 만나 보세요",
        imageUrl = "https://s.pstatic.net/static/www/mobile/edit/2016/0705/mobile_212852414260.png",
        url = "https://www.naver.com/"
    )
    OpenGraphCard(
        isLoading = false,
        openGraph = openGraph
    )
}