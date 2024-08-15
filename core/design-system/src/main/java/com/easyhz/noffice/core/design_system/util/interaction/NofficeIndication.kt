package com.easyhz.noffice.core.design_system.util.interaction

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import com.easyhz.noffice.core.design_system.theme.Grey300
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

object NofficeIndicationNodeFactory : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return NofficeIndicationNode(interactionSource, Dispatchers.Main.immediate)
    }

    override fun hashCode(): Int = -1

    override fun equals(other: Any?) = other === this
}

private class NofficeIndicationNode(
    private val interactionSource: InteractionSource,
    private val dispatcher: CoroutineContext
) : Modifier.Node(), DrawModifierNode {
    val animatedScalePercent = Animatable(1f)
    val alpha = Animatable(0f)

    private suspend fun animateToPressed() = withContext(dispatcher) {
        launch { animatedScalePercent.animateTo(0.95f, tween()) }
        launch { alpha.animateTo(0.3f, tween()) }
    }

    private suspend fun animateToResting() = withContext(dispatcher) {
        launch { animatedScalePercent.animateTo(1f, tween()) }
        launch { alpha.animateTo(0f, tween()) }
    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> animateToPressed()
                    is PressInteraction.Release -> animateToResting()
                    is PressInteraction.Cancel -> animateToResting()
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {

        drawRoundRect(color = Grey300, alpha = alpha.value, cornerRadius = CornerRadius(x = 32f, y = 32f))
        scale(
            scale = animatedScalePercent.value,
        ) {
            this@draw.drawContent()
        }

    }
}