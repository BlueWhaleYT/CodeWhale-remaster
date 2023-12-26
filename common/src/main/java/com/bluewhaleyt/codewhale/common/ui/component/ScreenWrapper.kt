package com.bluewhaleyt.codewhale.common.ui.component

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SimpleScreenWrapper(
//    title: @Composable () -> Unit,
//    modifier: Modifier = Modifier,
//    columnModifier: Modifier = Modifier,
//    actions: @Composable (() -> Unit)? = null,
//    navigationIcon: @Composable (() -> Unit)? = null,
//    snackbarHost: @Composable (() -> Unit)? = null,
//    content: @Composable ColumnScope.(PaddingValues) -> Unit
//) {
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
//    val scrollState = rememberScrollState()
//
//    Scaffold(
//        modifier = modifier,
//        topBar = {
//            TopAppBar(
//                title = title,
//                actions = { actions?.invoke() },
//                navigationIcon = { navigationIcon?.invoke() },
//            )
//        },
//        snackbarHost = { snackbarHost?.invoke() }
//    ) {
//        Column(
//            modifier = columnModifier
//                .padding(it)
//                .nestedScroll(scrollBehavior.nestedScrollConnection)
//                .verticalScroll(scrollState)
//        ) {
//            content(it)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LargeScreenWrapper(
//    title: @Composable () -> Unit,
//    modifier: Modifier = Modifier,
//    columnModifier: Modifier = Modifier,
//    actions: @Composable (RowScope.(ScrollState) -> Unit)? = null,
//    navigationIcon: @Composable ((ScrollState) -> Unit)? = null,
//    snackbarHost: @Composable (() -> Unit)? = null,
//    content: @Composable ColumnScope.(PaddingValues) -> Unit
//) {
//    val windowInsets = TopAppBarDefaults.windowInsets
//    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
//        canScroll = { true }
//    )
//    val scrollState = rememberScrollState()
//
//    Scaffold(
//        modifier = modifier,
//        topBar = {
//            LargeTopAppBar(
//                title = title,
//                windowInsets = windowInsets,
//                scrollBehavior = scrollBehavior,
//                actions = { actions?.invoke(this, scrollState) },
//                navigationIcon = { navigationIcon?.invoke(scrollState) },
//            )
//        },
//        snackbarHost = { snackbarHost?.invoke() }
//    ) {
//        Column(
//            modifier = columnModifier
//                .padding(it)
//                .nestedScroll(scrollBehavior.nestedScrollConnection)
//                .verticalScroll(scrollState)
//        ) {
//            content(it)
//        }
//    }
//}
//
//@Composable
//fun AnimateIconButton(
//    visible: Boolean,
//    imageVector: ImageVector,
//    contentDescription: String? = null,
//    onClick: () -> Unit
//) {
//    AnimatedVisibility(visible = visible) {
//        IconButton(
//            onClick = onClick
//        ) {
//            Icon(
//                imageVector = imageVector,
//                contentDescription = contentDescription
//            )
//        }
//    }
//}