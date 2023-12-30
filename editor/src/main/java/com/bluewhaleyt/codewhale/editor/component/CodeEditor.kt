package com.bluewhaleyt.codewhale.editor.component

import android.content.Context
import android.graphics.Typeface
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import io.github.rosemoe.sora.lang.EmptyLanguage
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.text.Content
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

internal object CodeEditorTokens {
    const val TEXT_SiZE = CodeEditor.DEFAULT_TEXT_SIZE.toFloat()
    val COLOR_SCHEME = EditorColorScheme.getDefault()
    val LANGUAGE = EmptyLanguage()
}

class CodeEditorState internal constructor(
    private val context: Context,
    private val content: Content = Content(),
    private val textSize: Float = CodeEditorTokens.TEXT_SiZE,
    private val colorScheme: EditorColorScheme = CodeEditorTokens.COLOR_SCHEME,
    private val language: Language = CodeEditorTokens.LANGUAGE,
    private val typeface: Typeface = Typeface.createFromAsset(context.assets, "fonts/JetBrainsMono.ttf"),
) {
    var contentState by mutableStateOf(content)
    var textSizeState by mutableFloatStateOf(textSize)
    var colorSchemeState by mutableStateOf(colorScheme)
    var languageState by mutableStateOf(language)
    var typefaceState by mutableStateOf(typeface)
}

@Composable
fun rememberCodeEditorState() = remember {
    mutableStateOf<CodeEditor?>(null)
}

@Composable
fun rememberCodeEditorState(
    context: Context,
    content: Content = Content(),
    textSize: Float = CodeEditorTokens.TEXT_SiZE,
    colorScheme: EditorColorScheme = CodeEditorTokens.COLOR_SCHEME,
    language: Language = CodeEditorTokens.LANGUAGE
) = remember {
    CodeEditorState(
        context = context,
        content = content,
        textSize = textSize,
        colorScheme = colorScheme,
        language = language
    )
}

@Composable
fun ColumnScope.CodeEditor(
    modifier: Modifier = Modifier,
    state: CodeEditorState,
    onInitialize: ((CodeEditor) -> Unit)? = null
) {
    val context = LocalContext.current
    val editor = remember {
        CodeEditor(context)
            .also {
                onInitialize?.invoke(it)
            }
            .also {
                it.typefaceText = state.typefaceState
                it.typefaceLineNumber = state.typefaceState
            }
            .also {
                it.colorScheme = state.colorSchemeState
            }
    }
    AndroidView(
        factory = { editor },
        modifier = modifier
            .fillMaxSize()
            .weight(1f),
        onRelease = { it.release() }
    )
    LaunchedEffect(key1 = state.contentState) {
        editor.setText(state.contentState)
    }
    LaunchedEffect(key1 = state.textSizeState) {
        editor.setTextSize(state.textSizeState)
    }
    LaunchedEffect(key1 = state.colorSchemeState) {
        if (editor.colorScheme !== state.colorSchemeState )
            editor.colorScheme = state.colorSchemeState
    }
    LaunchedEffect(key1 = state.languageState) {
        editor.setEditorLanguage(state.languageState)
    }
    LaunchedEffect(state) {
        editor.lineNumberMarginLeft = 30f
        editor.setDividerMargin(30f, 0f)
        editor.setLineSpacing(2f, 1.5f)
    }
}

fun String.toContent() = Content(this)