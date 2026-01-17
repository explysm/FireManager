package app.revenge.manager.ui.widgets.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.revenge.manager.ui.theme.FireOrange
import app.revenge.manager.ui.theme.FireRed
import app.revenge.manager.utils.glow

@Composable
fun ThemePreview(
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(260.dp)
            .aspectRatio(9f / 16f)
            .clip(RoundedCornerShape(16.dp))
            .background(colorScheme.background)
            .border(
                1.dp,
                colorScheme.outline.copy(alpha = 0.2f),
                RoundedCornerShape(16.dp)
            )
    ) {
        // Fiery Background Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            FireRed.copy(alpha = 0.2f),
                            FireOrange.copy(alpha = 0.1f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Fake Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = 0.1f))
                )
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(8.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = 0.2f))
                )
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = 0.1f))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // App Icon
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .glow(FireOrange, radius = 16.dp, alpha = 0.4f)
                    .clip(CircleShape)
                    .background(colorScheme.surfaceVariant)
            )

            // Instance Selector Pill
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(16.dp)
                    .clip(CircleShape)
                    .background(colorScheme.surfaceVariant.copy(alpha = 0.8f))
            )

            // Action Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorScheme.primary)
            )

            // Changelog Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorScheme.surfaceVariant.copy(alpha = 0.5f))
            )
        }
    }
}
