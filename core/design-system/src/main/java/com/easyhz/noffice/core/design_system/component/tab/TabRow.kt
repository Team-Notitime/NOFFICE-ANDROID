package com.easyhz.noffice.core.design_system.component.tab

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.TabRowDefaults.IndicatorHeight
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.primaryContainerColor
import androidx.compose.material3.TabRowDefaults.primaryContentColor
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.tab.TabRowDefaults.tabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * **Material3 의 [TabRow]를 기반으로 탭의 간격을 주기 위해 새로 만든 컴포넌트.**
 *
 * Material Design fixed tabs.
 *
 * @param selectedTabIndex the index of the currently selected tab
 * @param modifier the [Modifier] to be applied to this tab row
 * @param containerColor the color used for the background of this tab row. Use [Color.Transparent]
 * to have no color.
 * @param contentColor the preferred color for content inside this tab row. Defaults to either the
 * matching content color for [containerColor], or to the current [LocalContentColor] if
 * [containerColor] is not a color from the theme.
 * @param tabSpacing Adjust the spacing of tab items
 * @param indicator the indicator that represents which tab is currently selected. By default this
 * will be a [TabRowDefaults.Indicator], using a [TabRowDefaults.tabIndicatorOffset] modifier to
 * animate its position. Note that this indicator will be forced to fill up the entire tab row, so
 * you should use [TabRowDefaults.tabIndicatorOffset] or similar to animate the actual drawn
 * indicator inside this space, and provide an offset from the start.
 * @param divider the divider displayed at the bottom of the tab row. This provides a layer of
 * separation between the tab row and the content displayed underneath.
 * @param tabs the tabs inside this tab row. Typically this will be multiple [Tab]s. Each element
 * inside this lambda will be measured and placed evenly across the row, each taking up equal space.
 */
@Composable
fun TabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = primaryContainerColor,
    contentColor: Color = primaryContentColor,
    tabSpacing: Dp = 8.dp,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
        if (selectedTabIndex < tabPositions.size) {
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
            )
        }
    },
    divider: @Composable () -> Unit = @Composable {
        VerticalDivider()
    },
    tabs: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.selectableGroup(),
        color = containerColor,
        contentColor = contentColor
    ) {
        SubcomposeLayout { constraints ->
            val tabSpacingPx = tabSpacing.roundToPx()
            val tabMeasurables = subcompose(TabSlots.Tabs, tabs)
            val tabCount = tabMeasurables.size
            val totalSpacing = (tabCount - 1) * tabSpacingPx

            val tabPlaceables = tabMeasurables.map {
                it.measure(
                    constraints.copy(
                        minWidth = 0,
                        maxWidth = Constraints.Infinity,
                        minHeight = 0,
                        maxHeight = Constraints.Infinity,
                    )
                )
            }

            val tabRowWidth = tabPlaceables.sumOf { it.width } + totalSpacing
            val tabRowHeight = tabPlaceables.maxOf { it.height }

            val tabPositions = mutableListOf<TabPosition>()
            var currentPosition = 0

            tabPlaceables.forEach { placeable ->
                tabPositions.add(TabPosition(currentPosition.toDp(), placeable.width.toDp()))
                currentPosition += placeable.width + tabSpacingPx
            }

            layout(tabRowWidth, tabRowHeight) {
                currentPosition = 0
                tabPlaceables.forEachIndexed { _, placeable ->
                    placeable.placeRelative(currentPosition, 0)
                    currentPosition += placeable.width + tabSpacingPx
                }

                subcompose(TabSlots.Divider, divider).forEach {
                    val placeable = it.measure(constraints.copy(minHeight = 0))
                    placeable.placeRelative(0, tabRowHeight - placeable.height)
                }

                subcompose(TabSlots.Indicator) {
                    indicator(tabPositions)
                }.forEach {
                    it.measure(Constraints.fixed(tabRowWidth, tabRowHeight)).placeRelative(0, 0)
                }
            }
        }
    }
}

@Immutable
class TabPosition internal constructor(val left: Dp, val width: Dp) {
    val right: Dp get() = left + width

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TabPosition) return false

        if (left != other.left) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }

    override fun toString(): String {
        return "TabPosition(left=$left, right=$right, width=$width)"
    }
}

/**
 * Contains default implementations and values used for TabRow.
 */
object TabRowDefaults {

    /**
     * Default indicator, which will be positioned at the bottom of the [TabRow], on top of the
     * divider.
     *
     * @param modifier modifier for the indicator's layout
     * @param height height of the indicator
     * @param color color of the indicator
     */
    @Composable
    fun Indicator(
        modifier: Modifier = Modifier,
        height: Dp = IndicatorHeight,
        color: Color = LocalContentColor.current
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(height)
                .background(color = color)
        )
    }

    /**
     * [Modifier] that takes up all the available width inside the [TabRow], and then animates
     * the offset of the indicator it is applied to, depending on the [currentTabPosition].
     *
     * @param currentTabPosition [TabPosition] of the currently selected tab. This is used to
     * calculate the offset of the indicator this modifier is applied to, as well as its width.
     */
    fun Modifier.tabIndicatorOffset(
        currentTabPosition: TabPosition
    ): Modifier = composed(
        inspectorInfo = debugInspectorInfo {
            name = "tabIndicatorOffset"
            value = currentTabPosition
        }
    ) {
        val currentTabWidth by animateDpAsState(
            targetValue = currentTabPosition.width,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
    }
}

private enum class TabSlots {
    Tabs,
    Divider,
    Indicator
}

/**
 * Class holding onto state needed for [ScrollableTabRow]
 */
private class ScrollableTabData(
    private val scrollState: ScrollState,
    private val coroutineScope: CoroutineScope
) {
    private var selectedTab: Int? = null

    fun onLaidOut(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<TabPosition>,
        selectedTab: Int
    ) {
        // Animate if the new tab is different from the old tab, or this is called for the first
        // time (i.e selectedTab is `null`).
        if (this.selectedTab != selectedTab) {
            this.selectedTab = selectedTab
            tabPositions.getOrNull(selectedTab)?.let {
                // Scrolls to the tab with [tabPosition], trying to place it in the center of the
                // screen or as close to the center as possible.
                val calculatedOffset = it.calculateTabOffset(density, edgeOffset, tabPositions)
                if (scrollState.value != calculatedOffset) {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(
                            calculatedOffset,
                            animationSpec = ScrollableTabRowScrollSpec
                        )
                    }
                }
            }
        }
    }

    /**
     * @return the offset required to horizontally center the tab inside this TabRow.
     * If the tab is at the start / end, and there is not enough space to fully centre the tab, this
     * will just clamp to the min / max position given the max width.
     */
    private fun TabPosition.calculateTabOffset(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<TabPosition>
    ): Int = with(density) {
        val totalTabRowWidth = tabPositions.last().right.roundToPx() + edgeOffset
        val visibleWidth = totalTabRowWidth - scrollState.maxValue
        val tabOffset = left.roundToPx()
        val scrollerCenter = visibleWidth / 2
        val tabWidth = width.roundToPx()
        val centeredTabOffset = tabOffset - (scrollerCenter - tabWidth / 2)
        // How much space we have to scroll. If the visible width is <= to the total width, then
        // we have no space to scroll as everything is always visible.
        val availableSpace = (totalTabRowWidth - visibleWidth).coerceAtLeast(0)
        return centeredTabOffset.coerceIn(0, availableSpace)
    }
}

private val ScrollableTabRowMinimumTabWidth = 90.dp

/**
 * The default padding from the starting edge before a tab in a [ScrollableTabRow].
 */
private val ScrollableTabRowPadding = 52.dp

/**
 * [AnimationSpec] used when scrolling to a tab that is not fully visible.
 */
private val ScrollableTabRowScrollSpec: AnimationSpec<Float> = tween(
    durationMillis = 250,
    easing = FastOutSlowInEasing
)
