package com.bluewhaleyt.codewhale.common.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object InfoDefaults {
    @Composable
    fun IconContainer(
        icon: @Composable (() -> Unit)?
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
            Box(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                icon?.invoke()
            }
        }
    }

    @Composable
    fun TextContainer(
        text: @Composable (() -> Unit)?
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
            ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
                text?.invoke()
            }
        }
    }
}

@Composable
fun Info(
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) {
    val stroke = Stroke(width = 3f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    val color = MaterialTheme.colorScheme.primary
    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    color = color,
                    style = stroke,
                    cornerRadius = CornerRadius(28.dp.toPx())
                )
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoDefaults.IconContainer(
            icon = icon
        )
        InfoDefaults.TextContainer(
            text = text
        )
    }
}

@Preview
@Composable
fun InfoPreview() {
    Info(
        icon = {
            Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null)
        },
        text = {
            Text(text = "Text")
        }
    )
}