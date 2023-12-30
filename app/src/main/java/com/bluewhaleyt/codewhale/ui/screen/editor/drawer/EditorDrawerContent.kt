package com.bluewhaleyt.codewhale.ui.screen.editor.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluewhaleyt.codewhale.common.ui.component.AnimateIconButton
import com.bluewhaleyt.codewhale.ui.screen.editor.drawer.tabpage.EditorDrawerComingSoonPage
import com.bluewhaleyt.codewhale.ui.screen.editor.drawer.tabpage.EditorDrawerFilesTabPage
import com.bluewhaleyt.codewhale.viewmodel.EditorDrawerViewModel
import com.bluewhaleyt.codewhale.viewmodel.EditorTabViewModel

class EditorDrawerTabItem(
    val icon: ImageVector,
    val text: String,
    val content: @Composable () -> Unit,
)

private fun drawerTabItems(
    editorTabViewModel: EditorTabViewModel
) = listOf(
    EditorDrawerTabItem(
        icon = Icons.Outlined.Folder,
        text = "Files",
        content = {
            EditorDrawerFilesTabPage(
                editorTabViewModel = editorTabViewModel
            )
        }
    ),
    EditorDrawerTabItem(
        icon = Icons.Outlined.Extension,
        text = "Plugins",
        content = {
            EditorDrawerComingSoonPage()
        }
    ),
    EditorDrawerTabItem(
        icon = Icons.Outlined.Settings,
        text = "Settings",
        content = {
            EditorDrawerComingSoonPage()
        }
    )
)

@Composable
fun EditorDrawerContent(
    viewModel: EditorDrawerViewModel = viewModel(),
    editorTabViewModel: EditorTabViewModel = viewModel(),
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .width(350.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Scaffold {
            Column(
                modifier = Modifier.padding(it)
            ) {
                Row {
                    EditorDrawerNavigationRail(
                        navController = navController,
                        drawerState = drawerState
                    )
                    NavHost(
                        navController = navController,
                        startDestination = drawerTabItems(editorTabViewModel)[0].text,
                    ) {
                        drawerTabItems(editorTabViewModel).forEachIndexed { _, item ->
                            composable(item.text) {
                                item.content()
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditorDrawerNavigationRail(
    viewModel: EditorDrawerViewModel = viewModel(),
    editorTabViewModel: EditorTabViewModel = viewModel(),
    navController: NavController,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    NavigationRail(
        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.38f),
        modifier = Modifier
            .width(56.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = MaterialTheme.shapes.extraLarge.topEnd,
                    bottomEnd = MaterialTheme.shapes.extraLarge.bottomEnd,
                    topStart = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
            ),
        windowInsets = WindowInsets(top = 4.dp, bottom = 4.dp),
    ) {
        AnimateIconButton(
            imageVector = viewModel.getDrawerToggleIcon(
                drawerState = drawerState
            ),
            contentDescription = viewModel.getDrawerToggleContentDescription(
                drawerState = drawerState
            ),
        ) {
            viewModel.toggleDrawer(
                drawerState = drawerState,
                scope = scope
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        drawerTabItems(editorTabViewModel).forEachIndexed { i, item ->
            NavigationRailItem(
                selected = viewModel.drawerTabSelectedIndex.value == i,
                onClick = {
                    viewModel.onNavigationRailItemSelected(
                        index = i,
                        item = item,
                        navController = navController
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.text
                    )
                },
                modifier = Modifier.size(56.dp)
            )
        }
    }
}