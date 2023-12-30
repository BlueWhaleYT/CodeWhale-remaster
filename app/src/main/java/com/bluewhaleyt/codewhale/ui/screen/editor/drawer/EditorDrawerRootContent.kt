package com.bluewhaleyt.codewhale.ui.screen.editor.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluewhaleyt.codewhale.common.ui.component.AnimateIconButton
import com.bluewhaleyt.codewhale.common.ui.component.ScreenWrapper
import com.bluewhaleyt.codewhale.ui.screen.editor.layout.EditorTabLayout
import com.bluewhaleyt.codewhale.viewmodel.EditorDrawerViewModel

@Composable
fun EditorDrawerRootContent(
    editorDrawerViewModel: EditorDrawerViewModel = viewModel(),
    drawerState: DrawerState
) {
    EditorDrawerRootContentContainer(
        drawerState = drawerState
    ) {
        EditorTabLayout()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditorDrawerRootContentContainer(
    editorDrawerViewModel: EditorDrawerViewModel = viewModel(),
    drawerState: DrawerState,
    content: @Composable ColumnScope.(PaddingValues) -> Unit
) {
    val scope = rememberCoroutineScope()

    ScreenWrapper(
        title = { Text(text = "CodeWhale") },
        leadingContainer = {
            AnimateIconButton(
                imageVector = editorDrawerViewModel.getDrawerToggleIcon(
                    drawerState = drawerState
                )
            ) {
                editorDrawerViewModel.toggleDrawer(
                    drawerState = drawerState,
                    scope = scope
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            content(it)
        }
    }
}