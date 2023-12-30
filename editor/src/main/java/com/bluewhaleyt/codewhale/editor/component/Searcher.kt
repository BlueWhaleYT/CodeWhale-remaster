package com.bluewhaleyt.codewhale.editor.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.EditorSearcher.SearchOptions
import java.util.regex.PatternSyntaxException

private var replaceVisible by mutableStateOf(false)

@Composable
fun Searcher(
    modifier: Modifier = Modifier,
    editor: CodeEditor?,
    searchOptions: SearchOptions
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SearchRow(
            editor = editor,
            searchOptions = searchOptions
        )
        AnimatedVisibility(visible = replaceVisible) {
            ReplaceRow(
                editor = editor
            )
        }
    }
}

@Composable
private fun SearchRow(
    editor: CodeEditor?,
    searchOptions: SearchOptions
) {
    var searchValue by rememberSaveable { mutableStateOf("") }

    fun performSearch() {
        editor?.let {
            if (searchValue.isNotEmpty()) {
                try {
                    it.searcher.search(searchValue, searchOptions)
                } catch (e: PatternSyntaxException) {
                    e.printStackTrace()
                }
            } else it.searcher.stopSearch()
        }
    }

    ItemRow {
        val icon = if (replaceVisible) Icons.Default.ExpandLess else Icons.Default.ExpandMore
        ItemButton(imageVector = icon) {
            replaceVisible = !replaceVisible
        }
        ItemInput(
            value = searchValue,
            onValueChange = {
                searchValue = it
                performSearch()
            },
            placeholder = "Search"
        )
        ItemButton(
            imageVector = Icons.Default.ArrowUpward,
            enabled = searchValue.isNotEmpty()
        ) {
            editor?.searcher?.gotoPrevious()
        }
        ItemButton(
            imageVector = Icons.Default.ArrowDownward,
            enabled = searchValue.isNotEmpty()
        ) {
            editor?.searcher?.gotoNext()
        }
    }
}

@Composable
private fun ReplaceRow(
    editor: CodeEditor?
) {
    var replaceValue by rememberSaveable { mutableStateOf("") }

    ItemRow {
        Spacer(modifier = Modifier.size(48.dp))
        ItemInput(
            value = replaceValue,
            onValueChange = {
                replaceValue = it
            },
            placeholder = "Replace"
        )
        ItemButton(
            imageVector = Icons.Default.Done,
            enabled = replaceValue.isNotEmpty()
        ) {
            editor?.searcher?.replaceThis(replaceValue)
        }
        ItemButton(
            imageVector = Icons.Default.DoneAll,
            enabled = replaceValue.isNotEmpty()
        ) {
            editor?.searcher?.replaceAll(replaceValue)
        }
    }
}

@Composable
private fun ItemRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        content()
    }
}

@Composable
private fun ItemButton(
    imageVector: ImageVector,
    contentDescription: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun ItemInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
}