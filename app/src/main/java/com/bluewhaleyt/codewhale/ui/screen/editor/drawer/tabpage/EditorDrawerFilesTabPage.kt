package com.bluewhaleyt.codewhale.ui.screen.editor.drawer.tabpage

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.BonsaiStyle
import cafe.adriel.bonsai.filesystem.FileSystemTree
import com.bluewhaleyt.codewhale.common.ext.externalStoragePath
import com.bluewhaleyt.codewhale.common.ext.getFileHelper
import com.bluewhaleyt.codewhale.common.icon.MaterialFileIcons
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Document
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Folder
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Image
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Java
import com.bluewhaleyt.codewhale.common.utils.file.FileTypes
import com.bluewhaleyt.codewhale.viewmodel.EditorDrawerFilesTabPageViewModel
import com.bluewhaleyt.codewhale.viewmodel.EditorTabViewModel
import okio.Path
import java.io.File

@Composable
fun EditorDrawerFilesTabPage(
    viewModel: EditorDrawerFilesTabPageViewModel = viewModel(),
    editorTabViewModel: EditorTabViewModel,
) {
    val context = LocalContext.current
    EditorDrawerTabPageContainer(
        topContent = {
            Text(text = "Files")
        }
    ) {
        val tree = FileSystemTree(
            rootPath = File(context.getFileHelper().externalStoragePath())
        )

        Bonsai(
            tree = tree,
            style = FileSystemBonsaiStyleCustom(),
            onClick = { node ->
                viewModel.onFileItemClick(
                    editorTabViewModel = editorTabViewModel,
                    tree = tree,
                    node = node
                )
            },
            onLongClick = { node ->
                viewModel.onFileItemLongClick(
                    editorTabViewModel = editorTabViewModel,
                    tree = tree,
                    node = node
                )
            }
        )
//        AnimatedVisibility(visible = viewModel.showFilePreviewPopup.value) {
//            Popup {
//                viewModel.file.value?.let {
//                    Column(
//                        modifier = Modifier.size(100.dp)
//                    ) {
//                        EditorPage(
//                            file = it,
//                        )
//                    }
//                }
//            }
//        }
    }
}

@Composable
private fun FileSystemBonsaiStyleCustom(): BonsaiStyle<Path> = BonsaiStyle(
    nodeNameStartPadding = 4.dp,
//    nodeCollapsedIcon = { node ->
//        rememberAsyncImagePainter(
//            model = ImageRequest.Builder(LocalContext.current)
//                .decoderFactory(SvgDecoder.Factory())
//                .data(node.content.toFile().path.getFileIcon())
//                .size(Size(40, 40))
//                .build(),
//        )
//    },
    nodeCollapsedIcon = { node ->
        rememberVectorPainter(
            image = fileIcon(node.content.toFile())
        )
    },
    nodeIconSize = 18.dp,
    nodeNameTextStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
    toggleIconColorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.outline),
    nodeSelectedBackgroundColor = Color.Transparent
)

@Composable
fun fileIcon(file: File) = if (file.isDirectory) {
    MaterialFileIcons.Folder(MaterialTheme.colorScheme)
} else {
    when (file.extension) {
        in FileTypes.BitmapImage.extensions -> MaterialFileIcons.Image
        in FileTypes.Java.extensions -> MaterialFileIcons.Java
        else -> MaterialFileIcons.Document
    }
}