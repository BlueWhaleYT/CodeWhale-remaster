package com.bluewhaleyt.codewhale.ui.screen.editor.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluewhaleyt.codewhale.viewmodel.EditorDrawerViewModel

@Composable
fun EditorDrawer(
    viewModel: EditorDrawerViewModel = viewModel(),
    drawerState: DrawerState
) {
    ModalNavigationDrawer(
        gesturesEnabled = false,
        drawerState = drawerState,
        drawerContent = {
            EditorDrawerContent(
                drawerState = drawerState
            )
        },
        content = {
            EditorDrawerRootContent(
                drawerState = drawerState
            )
        },
    )
}