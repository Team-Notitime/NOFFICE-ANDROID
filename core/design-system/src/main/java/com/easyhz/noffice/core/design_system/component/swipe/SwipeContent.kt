//package com.easyhz.noffice.core.design_system.component.swipe
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.shrinkVertically
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.DraggableAnchors
//import androidx.compose.foundation.gestures.Orientation
//import androidx.compose.foundation.gestures.anchoredDraggable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.SideEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import com.easyhz.noffice.core.design_system.extension.noRippleClickable
//import com.easyhz.noffice.core.design_system.theme.SubBody12
//import com.easyhz.noffice.core.design_system.theme.White
//import com.easyhz.noffice.core.design_system.util.interaction.useInteraction
//import com.easyhz.noffice.core.design_system.util.swipe.DragValue
//import com.easyhz.noffice.core.design_system.util.swipe.SwipeValue
//import com.easyhz.noffice.core.design_system.util.swipe.moveToCenter
//import com.easyhz.noffice.core.design_system.util.swipe.rememberAnchoredDraggableState
//import kotlinx.coroutines.delay
//import kotlin.math.roundToInt
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun SwipeContent(
//    modifier: Modifier = Modifier,
//    swipeValue: SwipeValue,
//    dragThresholdsDp: Float = 56f,
//    velocityThresholdDp: Float = 100f,
//    positionalThresholdWeight: Float = 0.1f,
//    animationDuration: Int = 500,
//    contentToSwipe: @Composable (Modifier) -> Unit,
//) {
//    val density = LocalDensity.current
//    val width = LocalConfiguration.current.screenWidthDp.dp
//    var height by remember { mutableStateOf(0.dp) }
//    var isRemoved by remember { mutableStateOf(false) }
//    val anchoredDraggableState = rememberAnchoredDraggableState(
//        velocityThresholdDp = velocityThresholdDp,
//        positionalThresholdWeight = positionalThresholdWeight
//    )
//    val dragThresholdsPx = with(density) { dragThresholdsDp.dp.toPx() }
//    val anchors = DraggableAnchors<DragValue> {
//        DragValue.Start at -dragThresholdsPx
//        DragValue.Center at 0f
//    }
//    val draggableModifier = Modifier
//        .offset {
//            IntOffset(
//                x = anchoredDraggableState
//                    .requireOffset()
//                    .roundToInt(),
//                y = 0
//            )
//        }
//        .anchoredDraggable(anchoredDraggableState, Orientation.Horizontal)
//    LaunchedEffect(key1 = isRemoved) {
//        if (isRemoved) {
//            delay(animationDuration.toLong())
//            swipeValue.onClick()
//        }
//    }
//    SideEffect {
//        anchoredDraggableState.updateAnchors(anchors)
//    }
//    AnimatedVisibility(
//        modifier = modifier,
//        visible = !isRemoved,
//        exit = shrinkVertically(
//            animationSpec = tween(durationMillis = animationDuration),
//            shrinkTowards = Alignment.Top
//        ) + fadeOut()
//    ) {
//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            userScrollEnabled = false
//        ) {
//            //        swipeDirection.start?.let { start ->
//            //            AnimatedVisibility(visible = anchoredDraggableState.targetValue == DragValue.End) {
//            //                ActionButton(
//            //                    modifier = Modifier.matchParentSize(),
//            //                    value = start,
//            //                    moveToCenter = { anchoredDraggableState.dragToCenter() },
//            //                    dragThresholdsDp = dragThresholdsDp,
//            //                    margin = margin,
//            //                )
//            //            }
//            //        }
//            item {
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    contentToSwipe(
//                        draggableModifier
//                            .width(width)
//                            .onGloballyPositioned {
//                                height = with(density) { it.size.height.toDp() }
//                            })
//
//                    ActionButton(
//                        modifier = draggableModifier.height(height),
//                        value = swipeValue,
//                        moveToCenter = {
//                            anchoredDraggableState.moveToCenter()
//                        },
//                    ) {
//                        isRemoved = true
//                    }
//                }
//            }
//        }
//    }
////    Layout(
////        content = {
////            contentToSwipe(draggableModifier)
////            swipeDirection.start?.let { start ->
//////                AnimatedVisibility(visible = anchoredDraggableState.isAnimationRunning) {
////                    ActionButton(
////                        value = start,
////                        moveToCenter = { anchoredDraggableState.dragToCenter() },
////                        dragThresholdsDp = dragThresholdsDp,
////                    )
//////                }
////            }
////            swipeDirection.end?.let { end ->
//////                AnimatedVisibility(visible = anchoredDraggableState.targetValue == DragValue.Start) {
////                    ActionButton(
////                        value = end,
////                        moveToCenter = { anchoredDraggableState.dragToCenter() },
////                        dragThresholdsDp = dragThresholdsDp,
////                    )
//////                }
////            }
////        }
////    ) { measurableList, constraints ->
////        // ActionButton의 높이를 content의 높이로 맞춥니다.
////        val contentPlaceable = measurableList[0].measure(constraints)
////        val contentHeightConstraints = constraints.copy(
////            minHeight = contentPlaceable.height,
////            maxHeight = contentPlaceable.height
////        )
////        val startActionPlaceable = swipeDirection.end?.let {
////            measurableList[1].measure(contentHeightConstraints)
////        }
////
////        val endActionPlaceable = swipeDirection.start?.let {
////            measurableList[2].measure(contentHeightConstraints)
////        }
////
////        val startActionX = constraints.maxWidth - (startActionPlaceable?.width ?: 0) // End에 버튼 배치
////
////        layout(constraints.maxWidth, contentPlaceable.height) {
////            endActionPlaceable?.place(0, 0)
////            startActionPlaceable?.place(startActionX, 0)
////            contentPlaceable.placeRelative(0, 0)
////        }
////    }
//}
//
//@Composable
//fun ActionButton(
//    modifier: Modifier = Modifier,
//    value: SwipeValue,
//    moveToCenter: () -> Unit,
//    onClick: () -> Unit
//) {
//    val (interactionSource, scale) = useInteraction()
//
//    Box(
//        modifier = modifier
//            .width(300.dp)
//            .background(
//                value.backgroundColor,
//                value.roundedCornerShape
//            )
//            .scale(scale)
//            .noRippleClickable(interactionSource) {
//                moveToCenter()
//                onClick()
//            },
//        contentAlignment = value.alignment
//    ) {
//        Text(
//            modifier = Modifier.padding(horizontal = 12.dp),
//            text = value.text,
//            style = SubBody12,
//            color = White
//        )
//    }
//}
