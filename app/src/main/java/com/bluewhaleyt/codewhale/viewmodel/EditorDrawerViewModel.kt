package com.bluewhaleyt.codewhale.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.ui.screen.editor.drawer.EditorDrawerTabItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EditorDrawerViewModel : ViewModel() {

    private val _drawerValue = mutableStateOf(DrawerValue.Closed)
    val drawerValue: State<DrawerValue> = _drawerValue

    private val _drawerTabSelectedIndex = mutableStateOf(0)
    val drawerTabSelectedIndex: State<Int> = _drawerTabSelectedIndex

    fun getDrawerToggleIcon(
        drawerState: DrawerState
    ) = if (drawerState.isClosed) Icons.Default.Menu else Icons.Default.Close

    fun getDrawerToggleContentDescription(
        drawerState: DrawerState
    ) = if (drawerState.isClosed) "Open" else "Close"

    fun toggleDrawer(
        drawerState: DrawerState,
        scope: CoroutineScope
    ) {
        scope.launch {
            if (drawerState.isClosed) drawerState.open()
                else drawerState.close()
        }
    }

    fun onNavigationRailItemSelected(
        index: Int,
        item: EditorDrawerTabItem,
        navController: NavController
    ) {
        _drawerTabSelectedIndex.value = index
        navController.navigate(item.text)
    }

}