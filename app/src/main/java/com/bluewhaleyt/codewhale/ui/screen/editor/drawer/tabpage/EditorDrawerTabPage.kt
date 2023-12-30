package com.bluewhaleyt.codewhale.ui.screen.editor.drawer.tabpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditorDrawerTabPageContainer(
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    topContent: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = rowModifier.padding(
                horizontal = 16.dp,
                vertical = 20.dp
            )
        ) {
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleLarge) {
                topContent()
            }
        }
        content()
    }
}

@Composable
fun EditorDrawerComingSoonPage() {
    EditorDrawerTabPageContainer(
        topContent = {}
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Coming soon")
        }
    }
}