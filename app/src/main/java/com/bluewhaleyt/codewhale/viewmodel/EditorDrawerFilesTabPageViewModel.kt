package com.bluewhaleyt.codewhale.viewmodel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cafe.adriel.bonsai.core.node.LeafNode
import cafe.adriel.bonsai.core.node.Node
import cafe.adriel.bonsai.core.tree.Tree
import com.bluewhaleyt.codewhale.common.utils.logD
import okio.Path
import java.io.File

class EditorDrawerFilesTabPageViewModel : ViewModel() {

    private val _rootFileUri = mutableStateOf<Uri?>(null)
    val rootFileUri: State<Uri?> = _rootFileUri

    private val _showFilePreviewPopup = mutableStateOf(false)
    val showFilePreviewPopup: State<Boolean> = _showFilePreviewPopup

    private val _file = mutableStateOf<File?>(null)
    val file: State<File?> = _file

    fun onFileItemClick(
        editorTabViewModel: EditorTabViewModel,
        tree: Tree<Path>,
        node: Node<Path>
    ) {
        if (node is LeafNode) {
            editorTabViewModel.addTab(node.content.toFile())
            this logD "Tab list size: ${editorTabViewModel.tabList.size}"
        } else {
            tree.toggleExpansion(node)
        }
    }

    fun onFileItemLongClick(
        editorTabViewModel: EditorTabViewModel,
        tree: Tree<Path>,
        node: Node<Path>
    ) {
        _file.value = node.content.toFile()
        if (node is LeafNode) {
            _showFilePreviewPopup.value = true
        }
    }

}