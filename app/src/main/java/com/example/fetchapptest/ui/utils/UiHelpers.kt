package com.example.fetchapptest.ui.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.fetchapptest.ui.theme.skeletonDark
import com.example.fetchapptest.ui.theme.skeletonLight

fun Modifier.shimmerLoadingAnimation(
    shape: RoundedCornerShape, durationMillis: Int = 5000,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 90f
): Modifier =
    composed {
        var size by remember { mutableStateOf(IntSize.Zero) }

        val transition = rememberInfiniteTransition(label = "Animates the background")

        val startOffsetX by transition.animateFloat(
            initialValue = 0f,
            targetValue = (widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "Animates the background"
        )

        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    skeletonLight,
                    skeletonDark
                ),
                start = Offset(startOffsetX - widthOfShadowBrush, 0f),
                end = Offset(startOffsetX, angleOfAxisY),
                tileMode = TileMode.Mirror
            ),
            shape = shape
        ).onGloballyPositioned {
            size = it.size
        }
    }