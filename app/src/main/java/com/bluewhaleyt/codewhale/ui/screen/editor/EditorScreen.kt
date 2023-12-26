package com.bluewhaleyt.codewhale.ui.screen.editor

import android.graphics.Typeface
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.ResetAppButton
import com.bluewhaleyt.codewhale.Screen
import com.bluewhaleyt.codewhale.common.ui.component.Info
import com.bluewhaleyt.codewhale.editor.CodeEditor
import com.bluewhaleyt.codewhale.editor.DynamicEditorColorScheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(
        pageCount = { 3 }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "CodeWhale") },
                actions = {
                    ResetAppButton()
                    IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            ScrollableTabRow(
                modifier = Modifier.height(45.dp),
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp
            ) {
                for (i in 0..2) {
                    Tab(
                        modifier = Modifier.height(45.dp),
                        selected = i == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = i
                            scope.launch { pagerState.animateScrollToPage(i) }
                        }
                    ) {
                        Text(text = "Item $i")
                    }
                }
            }

            var visible by remember { mutableStateOf(true) }
            AnimatedVisibility(visible = visible) {
                Info(
                    modifier = Modifier.padding(16.dp),
                    icon = { Icon(imageVector = Icons.Outlined.WarningAmber, contentDescription = null) },
                    text = { Text(text = "This app is built with Jetpack Compose") }
                )
                LaunchedEffect(key1 = visible) {
                    delay(3000)
                    visible = false
                }
            }

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                EditorPage(page)
            }
        }
    }
}

@Composable
fun EditorPage(i: Int) {
    val handleColor = LocalTextSelectionColors.current.handleColor
    val selectionBackgroundColor = LocalTextSelectionColors.current.backgroundColor
    val colorScheme = MaterialTheme.colorScheme

    val viewModel = viewModel<EditorViewModel>()

    CodeEditor(
        onInitialize = { editor ->
            editor.apply {
                this.colorScheme = DynamicEditorColorScheme(
                    colorScheme = colorScheme,
                    handleColor = handleColor,
                    selectionBackgroundColor = selectionBackgroundColor
                )
                setText(viewModel.editorText.value)
                setTextSize(18f)

                setDividerMargin(30f, 0f)
                lineNumberMarginLeft = 30f

                val font = Typeface.createFromAsset(context.assets, "fonts/JetBrainsMono.ttf")
                typefaceText = font
                typefaceLineNumber = font
            }
        }
    )
}