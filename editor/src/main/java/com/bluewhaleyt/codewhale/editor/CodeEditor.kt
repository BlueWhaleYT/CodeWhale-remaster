package com.bluewhaleyt.codewhale.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import io.github.rosemoe.sora.widget.CodeEditor

@Composable
fun CodeEditor(
    modifier: Modifier = Modifier,
    onInitialize: ((CodeEditor) -> Unit)? = null
) {
    val context = LocalContext.current
    val editor = remember {
        CodeEditor(context)
            .also {
                onInitialize?.invoke(it)
            }
    }
    AndroidView(
        factory = { editor },
        modifier = modifier.fillMaxSize(),
        onRelease = { it.release() }
    )
}