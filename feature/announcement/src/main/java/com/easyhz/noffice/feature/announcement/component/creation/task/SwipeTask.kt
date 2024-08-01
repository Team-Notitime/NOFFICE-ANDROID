//package com.easyhz.noffice.feature.announcement.component.creation.task
//
//import android.annotation.SuppressLint
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.gestures.AnchoredDraggableState
//import androidx.compose.foundation.gestures.DraggableAnchors
//import androidx.compose.foundation.gestures.snapping.SnapPosition
//import androidx.compose.foundation.interaction.DragInteraction
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.SwipeableState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.unit.dp
//
//enum class DragValue { Start, Center, End }
//
//@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
//@Composable
//internal fun SwipeTask(
//    modifier: Modifier = Modifier,
//) {
//    val density = LocalDensity.current
////    val anchors = DraggableAnchors {
////        DragValue.Start at with(density) { 100.dp.toPx() }
////        DragValue.Center at 0f
////        DragValue.End at with(density) { 100.dp.toPx() }
////    }
//    val squareSize = 80.dp
//    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
//    val anchors = mapOf(0f to 0, -sizePx to 1)
//
//    val state = remember {
//        AnchoredDraggableState(
//            initialValue = DragValue.Start,
//            positionalThreshold = { distance: Float -> distance * 0.5f },
//            velocityThreshold = { with(density) { 100.dp.toPx() } },
//            animationSpec = tween(),
//            decayAnimationSpec = tween()
//        )
//    }
////    val state = remember {  AnchoredDraggableState(
////        initialValue = 0,
////        positionalThreshold = { distance: Float -> distance * 0.5f },
////        velocityThreshold = { with(density) { 100.dp.toPx() } },
////        animationSpec = tween(),
////    )
////    }
//    val coroutineScope = rememberCoroutineScope()
////    val squareSize = 80.dp
////    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
////    val anchors = mapOf(0f to 0, -sizePx to 1)
////    Box(
////        modifier = Modifier
////        ...
////        .fillMaxWidth()
////        .height(IntrinsicSize.Min)
////        .swipeable(
////            state = swipeableState,
////            orientation = Orientation.Horizontal,
////            anchors = anchors,
////            thresholds = { _, _ -> FractionalThreshold(0.5f) },
////            velocityThreshold = 1000.dp
////        )
////    ) {
////        // 버튼 컴포즈
////        Box(
////            modifier = Modifier
////                .align(Alignment.CenterEnd)
////        ) {
////            TextButton(
////                modifier = Modifier
////                    .width(80.dp)
////                    .fillMaxHeight()
////                ...
////            onClick = {
////                coroutineScope.launch {
////                    swipeableState.animateTo(0, tween(600, 0))
////                }
////            }) {
////            Text(text = "삭제", ...)
////        }
////        }
////
////        // 카드 컴포즈
////        Box(modifier = Modifier
////            .offset {IntOffset(swipeableState.offset.value.roundToInt(), 0)}
////        ) {
////            Column {...} // 카드 컴포즈 구성
////        }
////    }
//}