package com.bluewhaleyt.codewhale.ui.screen.editor.page

import androidx.compose.runtime.Composable
import com.bluewhaleyt.codewhale.ui.screen.editor.layout.EditorLayout
import java.io.File

enum class EditorFileTabPageType {
    Default, ImagePreview
}

@Composable
fun EditorPageHandler(
    file: File,
    pageType: EditorFileTabPageType,
) {
    when (pageType) {
        EditorFileTabPageType.ImagePreview -> EditorImagePreviewPage(file)
        else -> EditorPage(file)
    }
}

@Composable
fun EditorPage(
    file: File
) {
    EditorLayout(file)
}