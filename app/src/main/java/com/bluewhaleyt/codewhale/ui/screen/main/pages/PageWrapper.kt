package com.bluewhaleyt.codewhale.ui.screen.main.pages

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageWrapper(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    columnModifier: Modifier = Modifier,
    actions: @Composable (RowScope.(ScrollState) -> Unit)? = null,
    navigationIcon: @Composable ((ScrollState) -> Unit)? = null,
    snackbarHost: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.(PaddingValues) -> Unit
) {
    val windowInsets = TopAppBarDefaults.windowInsets
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        canScroll = { true }
    )
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            LargeTopAppBar(
                title = title,
                windowInsets = windowInsets,
                scrollBehavior = scrollBehavior,
                actions = { actions?.invoke(this, scrollState) },
                navigationIcon = { navigationIcon?.invoke(scrollState) },
            )
        },
        snackbarHost = { snackbarHost?.invoke() }
    ) {
        Column(
            modifier = columnModifier
                .padding(it)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .verticalScroll(scrollState)
        ) {
            content(it)
        }
    }
}