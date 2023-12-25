package com.bluewhaleyt.codewhale.ui.screen.editor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EditorViewModel : ViewModel() {

    private val sampleCode = """
        public class Main {
            public static void main(String[] args) {
                System.out.println("Hello World!");
            }
        }
    """.trimIndent()

    private val _editorText = mutableStateOf(sampleCode)
    val editorText: MutableState<String> = _editorText

    fun onEditorTextChange(newText: String) {
        editorText.value = newText
    }

}