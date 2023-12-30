package com.bluewhaleyt.codewhale.editor.scheme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

@Composable
@RequiresApi(Build.VERSION_CODES.S)
fun SchemeDynamicColor(
    colorScheme: ColorScheme,
) = SchemeDynamicColor(
    colorScheme = colorScheme,
    handleColor = LocalTextSelectionColors.current.handleColor,
    selectionBackgroundColor = LocalTextSelectionColors.current.backgroundColor
)

@RequiresApi(Build.VERSION_CODES.S)
class SchemeDynamicColor(
    private val handleColor: Color?,
    private val selectionBackgroundColor: Color?,
    private val colorScheme: ColorScheme?
) : EditorColorScheme() {

    init {
        applyDefault()
    }

    override fun applyDefault() {
        if (colorScheme == null) return
        super.applyDefault()

        val alpha = 0.3f
        val alphaHigh = 0.6f

        setColor(WHOLE_BACKGROUND, colorScheme.inverseOnSurface.copy(alpha).toArgb())
        setColor(TEXT_NORMAL, colorScheme.onSurfaceVariant.toArgb())
        setColor(CURRENT_LINE, colorScheme.primaryContainer.copy(alpha).toArgb())

        setColor(LINE_NUMBER_BACKGROUND, WHOLE_BACKGROUND)
        setColor(LINE_NUMBER, colorScheme.onSurface.copy(alpha).toArgb())
        setColor(LINE_NUMBER_CURRENT, colorScheme.primary.toArgb())
        setColor(LINE_DIVIDER, Color.Transparent.toArgb())

        setColor(SELECTION_HANDLE, handleColor!!.toArgb())
        setColor(SELECTION_INSERT, handleColor.toArgb())
        setColor(SELECTED_TEXT_BACKGROUND, selectionBackgroundColor!!.toArgb())

        setColor(HIGHLIGHTED_DELIMITERS_BACKGROUND, colorScheme.primaryContainer.copy(alphaHigh).toArgb())
        setColor(HIGHLIGHTED_DELIMITERS_FOREGROUND, colorScheme.primary.toArgb())
        setColor(HIGHLIGHTED_DELIMITERS_UNDERLINE, Color.Transparent.toArgb())

        setColor(BLOCK_LINE, colorScheme.primaryContainer.copy(alphaHigh).toArgb())
        setColor(BLOCK_LINE_CURRENT, colorScheme.primary.copy(alphaHigh).toArgb())

        setColor(KEYWORD, colorScheme.primary.toArgb())
        setColor(IDENTIFIER_NAME, colorScheme.secondary.toArgb())
        setColor(IDENTIFIER_VAR, colorScheme.secondary.toArgb())
        setColor(OPERATOR, colorScheme.onSurfaceVariant.copy(alphaHigh).toArgb())
        setColor(FUNCTION_NAME, colorScheme.secondary.toArgb())
        setColor(LITERAL, colorScheme.onSurfaceVariant.toArgb())

        setColor(COMPLETION_WND_BACKGROUND, colorScheme.surface.toArgb())
        setColor(COMPLETION_WND_CORNER, colorScheme.surface.toArgb())
        setColor(COMPLETION_WND_TEXT_PRIMARY, colorScheme.primary.toArgb())
        setColor(COMPLETION_WND_TEXT_SECONDARY, colorScheme.onSurfaceVariant.toArgb())
        setColor(COMPLETION_WND_ITEM_CURRENT, colorScheme.primary.toArgb())

        setColor(MATCHED_TEXT_BACKGROUND, colorScheme.primary.copy(alpha).toArgb())

    }

}