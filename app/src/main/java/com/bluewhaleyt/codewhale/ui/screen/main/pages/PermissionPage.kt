package com.bluewhaleyt.codewhale.ui.screen.main.pages

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.common.ui.component.IconButton
import com.bluewhaleyt.codewhale.common.ui.component.Info
import com.bluewhaleyt.codewhale.common.utils.logD
import com.bluewhaleyt.codewhale.ui.screen.main.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PermissionPage(
    viewModel: MainViewModel = viewModel(),
    navController: NavController,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    PageWrapper(
        title = { Text(text = "Permission") },
        columnModifier = Modifier.padding(16.dp),
        navigationIcon = {
            IconButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                onClick = {
                    viewModel.onBackButtonClick(
                        scope = scope,
                        pagerState = pagerState
                    )
                }
            )
        }
    ) {
        Info(
            modifier = Modifier.padding(top = 5.dp, bottom = 40.dp),
            icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) },
            text = { Text(text = "The file will be saved into /storage/emulated/0/BlueWhaleYT/CodeWhale/ directory") }
        )

        val permissionContent = viewModel.permissionContent

        for (i in 0..2) {
            val permissionIcon = permissionContent.keys.elementAtOrNull(i)
            val permissionText = permissionContent.values.elementAtOrNull(i)

            ListItem(
                modifier = Modifier
                    .toggleable(
                        value = viewModel.permissionCheckBoxChecked.value,
                        onValueChange = { viewModel.onPermissionCheckBoxChecked(it) },
                        role = Role.Checkbox
                    ),
                headlineContent = { Text(text = permissionText.toString()) },
                leadingContent = {
                    permissionIcon?.let {
                        Icon(imageVector = it, contentDescription = null)
                    }
                },
                trailingContent = {
                    Checkbox(
                        checked = viewModel.permissionCheckBoxChecked.value,
                        onCheckedChange = { viewModel.onPermissionCheckBoxChecked(it) },
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        GrantPermissionButton(
            pagerState = pagerState,
            navController = navController
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun GrantPermissionButton(
    viewModel: MainViewModel = viewModel(),
    navController: NavController,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            "GrantPermissionButton" logD permissions.toString()
            viewModel.onGrantPermissionResult(
                context = context,
                navController = navController
            )
        }
    )

    Button(
        onClick = {
            viewModel.onGrantPermissionButtonClick(
                scope = scope,
                pagerState = pagerState,
                launcher = launcher
            )
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = viewModel.permissionCheckBoxChecked.value
    ) {
        Text(text = "Grant permissions")
    }
}