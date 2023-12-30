package com.bluewhaleyt.codewhale.ui.screen.editor.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.bluewhaleyt.codewhale.common.ext.getDocument
import com.bluewhaleyt.codewhale.common.ext.getFileContent
import com.bluewhaleyt.codewhale.common.ext.getFileHelper
import com.bluewhaleyt.codewhale.common.utils.isSdkAtLeast
import com.bluewhaleyt.codewhale.editor.component.CodeEditor
import com.bluewhaleyt.codewhale.editor.component.LinePositionInfoToolbar
import com.bluewhaleyt.codewhale.editor.component.Searcher
import com.bluewhaleyt.codewhale.editor.component.SymbolInputToolbar
import com.bluewhaleyt.codewhale.editor.component.rememberCodeEditorState
import com.bluewhaleyt.codewhale.editor.component.toContent
import com.bluewhaleyt.codewhale.editor.scheme.SchemeDynamicColor
import io.github.rosemoe.sora.widget.EditorSearcher
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula
import java.io.File

data class EditorLayoutComponent(
    val editor: Boolean = true,
    val symbolInputToolbar: Boolean = true,
    val linePositionInfoToolbar: Boolean = true,
    val searcher: Boolean = false,
)

@Composable
fun EditorLayout(
    file: File,
    component: EditorLayoutComponent = EditorLayoutComponent()
) {
    val context = LocalContext.current

    val colorScheme = if (isSdkAtLeast(31)) SchemeDynamicColor(colorScheme = MaterialTheme.colorScheme)
        else if (isSystemInDarkTheme()) SchemeDarcula() else EditorColorScheme()

    var editor by rememberCodeEditorState()
    val codeEditorState = rememberCodeEditorState(
        context = context,
        colorScheme = colorScheme,
        content = context.getFileHelper().getDocument(file).getFileContent().toContent()
    )

    Column {
        AnimatedVisibility(visible = component.searcher) {
            Searcher(
                editor = editor,
                searchOptions = EditorSearcher.SearchOptions(false, false)
            )
        }
        CodeEditor(
            state = codeEditorState,
            onInitialize = {
                editor = it
            }
        )
        AnimatedVisibility(visible = component.symbolInputToolbar) {
            SymbolInputToolbar(
                editor = editor
            )
        }
        AnimatedVisibility(visible = component.linePositionInfoToolbar) {
            LinePositionInfoToolbar(
                editor = editor
            )
        }
    }
}