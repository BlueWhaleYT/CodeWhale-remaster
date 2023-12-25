package com.bluewhaleyt.codewhale

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluewhaleyt.codewhale.ui.screen.editor.EditorScreen
import com.bluewhaleyt.codewhale.ui.screen.main.MainScreen

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ScreenNavController()
    }
}

sealed class Screen(
    val route: String
) {
    data object MainScreen: Screen("main_screen")
    data object EditorScreen: Screen("editor_screen")

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { append("/$it") }
    }
}

@Composable
fun ScreenNavController() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.EditorScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.EditorScreen.route) {
            EditorScreen(navController = navController)
        }
    }
}