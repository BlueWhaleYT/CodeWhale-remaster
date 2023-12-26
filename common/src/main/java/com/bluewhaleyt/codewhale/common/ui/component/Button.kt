package com.bluewhaleyt.codewhale.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButton(
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Composable
fun AnimateIconButton(
    visible: Boolean,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = visible) {
        IconButton(onClick = onClick) {
            Icon(imageVector = imageVector, contentDescription = contentDescription)
        }
    }
}