@file:OptIn(ExperimentalFoundationApi::class)

package com.bluewhaleyt.codewhale.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.DriveFileMoveRtl
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.R
import com.bluewhaleyt.codewhale.common.ui.component.AnimateIconButton
import com.bluewhaleyt.codewhale.common.ui.component.Info
import com.bluewhaleyt.codewhale.common.ui.component.LargeScreenWrapper
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController
) {
    val viewModel = viewModel<MainViewModel>()

    val pagerState = rememberPagerState(
        pageCount = { viewModel.pageCount }
    )
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> PrivacyPolicyPage(pagerState = pagerState)
            1 -> PermissionPage(pagerState = pagerState)
        }
    }
}

@Composable
fun PrivacyPolicyPage(
    pagerState: PagerState
) {
    val viewModel = viewModel<MainViewModel>().fromPrivacyPolicyPage()

    val context = LocalContext.current
    val fileContent = context.assets.open(viewModel.file)
        .bufferedReader().use { it.readText() }
        .replace("\$APP_NAME$", "CodeWhale")
        .replace("\$COMPANY_NAME$", "BlueWhaleYT")
        .replace("\$DATE$", "Dec 24, 2023")
    val scope = rememberCoroutineScope()

    LargeScreenWrapper(
        columnModifier = Modifier.padding(16.dp),
        title = { Text(text = "Privacy Policy") },
        actions = { scrollState ->
            AnimateIconButton(
                visible = scrollState.value == scrollState.maxValue,
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Continue",
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            )
        }
    ) {
        Info(
            modifier = Modifier.padding(top = 5.dp, bottom = 40.dp),
            icon = { Icon(imageVector = Icons.Outlined.FileDownload, contentDescription = null) },
            text = { Text(text = "Read the content carefully, and scroll to the end to continue") }
        )
        MarkdownText(
            markdown = fileContent,
            color = LocalContentColor.current,
            style = LocalTextStyle.current,
            fontResource = R.font.quicksand_medium
        )
    }
}

@Composable
fun PermissionPage(
    pagerState: PagerState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    var onRegister by remember { mutableStateOf(false) }
    if (onRegister) FilePermissionSystem.invoke(action = { launcher ->
        if (!FilePermissionSystem.arePermissionsGranted(context))
            scope.launch {
                launcher.launch(FilePermissionSystem.permissions)
                pagerState.scrollToPage(pagerState.currentPage - 1)
                pagerState.scrollToPage(pagerState.currentPage + 1)
            }
    })

    LargeScreenWrapper(
        columnModifier = Modifier.padding(16.dp),
        title = {
            Text(text = "Permission")
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState
            )
        },
        navigationIcon = {
            AnimateIconButton(
                visible = true,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            )
        }
    ) {
        Info(
            modifier = Modifier.padding(top = 5.dp, bottom = 40.dp),
            icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) },
            text = { Text(text = "The file will be saved into /storage/emulated/0/BlueWhaleYT/CodeWhale/ directory") }
        )

        Text(text = """
            Therefore, please first grant us and allow our application to access the file from your device's external storage, in order to have a complete experience of using the application.
            
            Please grant the following permissions to continue.
        """.trimIndent())

        var checked by rememberSaveable { mutableStateOf(false) }
        val permissionContent = mapOf(
            Icons.Outlined.Visibility to "Read files from the external Storage",
            Icons.Outlined.Edit to "Write files from the external Storage",
            Icons.Outlined.DriveFileMoveRtl to "All Files Access",
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        for (i in 0..2) {
            val permissionIcon = permissionContent.keys.elementAtOrNull(i)
            val permissionText = permissionContent.values.elementAtOrNull(i)

            ListItem(
                modifier = Modifier
                    .toggleable(
                        value = checked,
                        onValueChange = { checked = it },
                        role = Role.Checkbox
                    ),
                leadingContent = {
                    permissionIcon?.let {
                        Icon(imageVector = it, contentDescription = null)
                    }
                },
                headlineContent = { Text(text = permissionText.toString()) },
                trailingContent = {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onRegister = true },
            modifier = Modifier.fillMaxWidth(),
            enabled = checked
        ) {
            Text(text = "Grant permissions")
        }
    }
}