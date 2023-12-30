package com.bluewhaleyt.codewhale.ui.screen.editor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.ui.screen.editor.drawer.EditorDrawer
import com.bluewhaleyt.codewhale.viewmodel.EditorDrawerViewModel
import com.bluewhaleyt.codewhale.viewmodel.EditorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    viewModel: EditorViewModel = viewModel(),
    editorDrawerViewModel: EditorDrawerViewModel = viewModel(),
    navController: NavController
) {
    val drawerState = rememberDrawerState(
        initialValue = editorDrawerViewModel.drawerValue.value
    )

    EditorDrawer(
        drawerState = drawerState,
    )
}