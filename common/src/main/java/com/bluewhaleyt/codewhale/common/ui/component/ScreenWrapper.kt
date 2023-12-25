package com.bluewhaleyt.codewhale.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleScreenWrapper(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    columnModifier: Modifier = Modifier,
    actions: @Composable (() -> Unit)? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.(PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = title,
                actions = { actions?.invoke() },
                navigationIcon = { navigationIcon?.invoke() },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeScreenWrapper(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    columnModifier: Modifier = Modifier,
    actions: @Composable ((ScrollState) -> Unit)? = null,
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
                actions = { actions?.invoke(scrollState) },
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

@Composable
fun AnimateIconButton(
    visible: Boolean,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = visible) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription
            )
        }
    }
}