package com.bluewhaleyt.codewhale.editor.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rosemoe.sora.widget.CodeEditor

internal object SymbolInputToolbarTokens {
    val ITEM_WIDTH = 48.dp
    val ITEM_HEIGHT = 40.dp
    val SYMBOLS = setOf("{", "}", "(", ")", "[", "]", "<", ">", ",", ".", ";", "\"", "'", "?", "+", "-", "*", "/", "=")
}

@Composable
fun SymbolInputToolbar(
    modifier: Modifier = Modifier,
    editor: CodeEditor?,
    useSpace: Boolean = true,
    tabSize: Int = 4,
    items: Set<String> = SymbolInputToolbarTokens.SYMBOLS ,
    onInsert: (String) -> Unit = { editor?.insertText(it, it.length) }
) {
    val listItems = items.toList()
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        item {
            SymbolItem(
                displayText = "→",
                insertText = if (useSpace) " ".repeat(tabSize) else "\t",
                onInsert = onInsert
            )
        }
        items(listItems.size) { index ->
            val item = listItems[index]
            SymbolItem(
                displayText = item,
                insertText = item,
                onInsert = onInsert
            )
        }
    }
}

@Composable
private fun SymbolItem(
    displayText: String,
    insertText: String,
    onInsert: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .defaultMinSize(minWidth = SymbolInputToolbarTokens.ITEM_WIDTH)
            .height(SymbolInputToolbarTokens.ITEM_HEIGHT)
            .clickable {
                onInsert(insertText)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = displayText,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
        )
    }
}