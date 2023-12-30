package com.bluewhaleyt.codewhale.editor.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.rosemoe.sora.event.SelectionChangeEvent
import io.github.rosemoe.sora.widget.CodeEditor

@Composable
fun LinePositionInfoToolbar(
    modifier: Modifier = Modifier,
    editor: CodeEditor?
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        editor?.let {
            var ln by remember { mutableStateOf(0) }
            var col by remember { mutableStateOf(0) }
            var selectedWordCount by remember { mutableStateOf(0) }

            it.subscribeEvent(SelectionChangeEvent::class.java) { _, _ ->
                ln = 1 + it.cursor.leftLine
                col = 1 + it.cursor.leftColumn
                selectedWordCount = it.cursor.right - it.cursor.left
            }

            val flag = when {
                it.cursor.isSelected -> "(Selected: $selectedWordCount)"
                it.searcher.hasQuery() -> {
                    val idx = it.searcher.currentMatchedPositionIndex
                    val matchText = when (val count = it.searcher.matchedPositionCount) {
                        0 -> "no match"
                        1 -> "1 match"
                        else -> "$count matches"
                    }
                    if (idx == -1) "($matchText)"
                    else "(${idx + 1} of $matchText)"
                }
                else -> ""
            }

            Text(text = "Line: $ln, Col: $col $flag")
        }
    }
}