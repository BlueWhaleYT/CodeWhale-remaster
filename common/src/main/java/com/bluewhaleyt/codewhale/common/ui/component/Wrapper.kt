package com.bluewhaleyt.codewhale.common.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageWrapper(
    modifier: Modifier = Modifier,
    columnModifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    leadingContainer: @Composable ((ScrollState) -> Unit)? = null,
    trailingContainer: @Composable (RowScope.(ScrollState) -> Unit)? = null,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        canScroll = { true }
    ),
    content: @Composable ColumnScope.(PaddingValues) -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            LargeTopAppBar(
                title = { title?.invoke() },
                windowInsets = windowInsets,
                scrollBehavior = scrollBehavior,
                actions = {
                    trailingContainer?.invoke(this, scrollState)
                },
                navigationIcon = {
                    leadingContainer?.invoke(scrollState)
                }
            )
        }
    ) {
        Column(
            modifier = columnModifier
                .padding(it)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .verticalScroll(scrollState),
            content = { content(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenWrapper(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    leadingContainer: @Composable (() -> Unit)? = null,
    trailingContainer: @Composable (RowScope.() -> Unit)? = null,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.imePadding(),
        topBar = {
            TopAppBar(
                title = { title?.invoke() },
                windowInsets = windowInsets,
                scrollBehavior = scrollBehavior,
                actions = {
                    trailingContainer?.invoke(this)
                },
                navigationIcon = {
                    leadingContainer?.invoke()
                }
            )
        }
    ) {
        content(it)
    }
}