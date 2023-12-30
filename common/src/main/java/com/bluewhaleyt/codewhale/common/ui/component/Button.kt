package com.bluewhaleyt.codewhale.common.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun AnimateIconButton(
    modifier: Modifier = Modifier,
    visible: Boolean,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = visible) {
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(imageVector = imageVector, contentDescription = contentDescription)
        }
    }
}

@Composable
fun AnimateIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    AnimatedContent(
        targetState = imageVector,
        label = "Animate Icon",
        transitionSpec = {
            fadeIn(
                animationSpec = tween(500)
            ) togetherWith
                    fadeOut(
                        animationSpec = tween(500)
                    )
        }
    ) { targetIcon ->
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(imageVector = targetIcon, contentDescription = contentDescription)
        }
    }
}