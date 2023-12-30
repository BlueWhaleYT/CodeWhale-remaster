package com.bluewhaleyt.codewhale.ui.screen.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.Screen
import com.bluewhaleyt.codewhale.common.ext.getFileHelper
import com.bluewhaleyt.codewhale.common.ext.requestAllFileAccess
import com.bluewhaleyt.codewhale.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            viewModel.onLauncherResult(
                permissions = permissions
            )
        }
    )
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        launcher.launch(viewModel.permissions)
    }

    context.getFileHelper().requestAllFileAccess()
    navController.navigate(Screen.EditorScreen.route)
}