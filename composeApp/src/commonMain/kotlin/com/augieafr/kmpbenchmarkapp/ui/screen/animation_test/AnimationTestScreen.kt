package com.augieafr.kmpbenchmarkapp.ui.screen.animation_test

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.augieafr.benchmarkapp.ui.component.LargeSpace
import com.augieafr.benchmarkapp.ui.component.MediumSpace
import kotlin.math.roundToInt

@Composable
fun AnimationTestScreen(modifier: Modifier = Modifier) {
    var isAnimating by remember { mutableStateOf(false) }
    var animationStarted by remember { mutableStateOf(false) }
    var completedAnimations by remember { mutableStateOf(0) }
    val totalAnimations = 20

    val allAnimationsComplete = completedAnimations >= totalAnimations

    // Reset counter when starting new animation
    LaunchedEffect(isAnimating) {
        if (isAnimating) {
            completedAnimations = 0
        }
    }

    // Update status when all animations complete
    LaunchedEffect(allAnimationsComplete) {
        if (allAnimationsComplete && isAnimating) {
            isAnimating = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Section
        AnimationHeaderSection(
            isAnimating = isAnimating,
            animationStarted = animationStarted,
            completedAnimations = completedAnimations,
            totalAnimations = totalAnimations
        )

        // Action Buttons
        AnimationActionButtons(
            onStartAnimation = {
                isAnimating = true
                animationStarted = true
            },
            isAnimating = isAnimating
        )

        LargeSpace()

        // Animation Container
        AnimationContainer(
            isAnimating = isAnimating,
            onCardAnimationComplete = {
                completedAnimations++
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun AnimationHeaderSection(
    isAnimating: Boolean,
    animationStarted: Boolean,
    completedAnimations: Int,
    totalAnimations: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Animation Performance Test",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            MediumSpace()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Elements: 20 cards",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Grid: 4×5 layout",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (isAnimating || completedAnimations > 0) {
                        Text(
                            text = "Progress: $completedAnimations/$totalAnimations",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // Status Indicator
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = when {
                                    isAnimating -> Color.Yellow
                                    animationStarted && completedAnimations >= totalAnimations -> Color.Green
                                    animationStarted -> Color.Blue
                                    else -> Color.Gray
                                },
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when {
                            isAnimating -> "Animating"
                            animationStarted && completedAnimations >= totalAnimations -> "Complete"
                            animationStarted -> "Partial"
                            else -> "Ready"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimationActionButtons(
    onStartAnimation: () -> Unit,
    isAnimating: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Start Animation Button
        ElevatedButton(
            onClick = onStartAnimation,
            enabled = !isAnimating,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Start Animation Performance Test")
        }

        // Show explanation when animating
        if (isAnimating) {
            Text(
                text = "Animation in progress - testing simultaneous translation, rotation, alpha, and scale transformations",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun AnimationContainer(
    isAnimating: Boolean,
    onCardAnimationComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    // Card dimensions
    val cardSize = 60.dp
    val cardSpacing = 8.dp

    // Grid configuration (4 columns, 5 rows)
    val columns = 4
    val rows = 5

    // Calculate grid dimensions
    val gridWidth = (cardSize * columns) + (cardSpacing * (columns - 1))
    val gridHeight = (cardSize * rows) + (cardSpacing * (rows - 1))

    Box(modifier = modifier) {
        // Create 20 animated cards
        repeat(20) { index ->
            val row = index / columns
            val column = index % columns

            // Calculate target position in grid (relative to container center)
            val targetOffsetX =
                (column * (cardSize + cardSpacing).value).dp - gridWidth / 2 + cardSize / 2
            val targetOffsetY =
                (row * (cardSize + cardSpacing).value).dp - gridHeight / 2 + cardSize / 2

            AnimatedCard(
                index = index,
                isAnimating = isAnimating,
                targetOffset = Offset(
                    with(density) { targetOffsetX.toPx() },
                    with(density) { targetOffsetY.toPx() }
                ),
                cardSize = cardSize,
                onAnimationComplete = onCardAnimationComplete,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun AnimatedCard(
    index: Int,
    isAnimating: Boolean,
    targetOffset: Offset,
    cardSize: Dp,
    onAnimationComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animation duration with slight stagger for visual effect
    val animationDuration = 2000 + (index * 50) // 2-3 seconds with stagger

    // Track if this card's animation has completed
    var hasCompletedAnimation by remember { mutableStateOf(false) }

    // Animate position - stay in final position after animation
    val animatedOffset by animateOffsetAsState(
        targetValue = if (isAnimating || hasCompletedAnimation) targetOffset else Offset.Zero,
        animationSpec = tween(durationMillis = animationDuration),
        label = "position_$index"
    )

    // Animate rotation - stay at final rotation after animation
    val animatedRotation by animateFloatAsState(
        targetValue = if (isAnimating || hasCompletedAnimation) 360f else 0f,
        animationSpec = tween(durationMillis = animationDuration),
        label = "rotation_$index"
    )

    // Animate scale - stay at final scale after animation
    val animatedScale by animateFloatAsState(
        targetValue = if (isAnimating || hasCompletedAnimation) 1f else 0.3f,
        animationSpec = tween(durationMillis = animationDuration),
        label = "scale_$index"
    )

    // Animate alpha - stay at final alpha after animation
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isAnimating || hasCompletedAnimation) 1f else 0.7f,
        animationSpec = tween(durationMillis = animationDuration),
        finishedListener = {
            // Only call completion callback once per animation cycle
            if (isAnimating && !hasCompletedAnimation) {
                hasCompletedAnimation = true
                onAnimationComplete()
            }
        },
        label = "alpha_$index"
    )

    // Reset completion flag when animation starts
    LaunchedEffect(isAnimating) {
        if (isAnimating) {
            hasCompletedAnimation = false
        }
    }

    Card(
        modifier = modifier
            .size(cardSize)
            .offset {
                IntOffset(
                    x = animatedOffset.x.roundToInt(),
                    y = animatedOffset.y.roundToInt()
                )
            }
            .scale(animatedScale)
            .rotate(animatedRotation)
            .alpha(animatedAlpha)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Default.Animation,
                contentDescription = "Card ${index + 1}",
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "Card ${index + 1}",
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}