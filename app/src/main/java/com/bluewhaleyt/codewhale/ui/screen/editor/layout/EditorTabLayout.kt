package com.bluewhaleyt.codewhale.ui.screen.editor.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluewhaleyt.codewhale.ui.screen.editor.drawer.tabpage.fileIcon
import com.bluewhaleyt.codewhale.ui.screen.editor.page.EditorPageHandler
import com.bluewhaleyt.codewhale.viewmodel.EditorTabContent
import com.bluewhaleyt.codewhale.viewmodel.EditorTabViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditorTabLayout(
    viewModel: EditorTabViewModel = viewModel(),
) {
    val pagerState = rememberPagerState(
        pageCount = { viewModel.tabList.size }
    )

    if (viewModel.tabList.size > 0) {
        ScrollableTabRow(
            selectedTabIndex = viewModel.selectedTabIndex.value,
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(),
            edgePadding = 0.dp
        ) {
            viewModel.tabList.forEachIndexed { i, tab ->
                FileTabItem(
                    index = i,
                    tab = tab,
                    pagerState = pagerState
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) {
            EditorPageHandler(
                file = viewModel.tabList[viewModel.selectedTabIndex.value].file,
                pageType = viewModel.tabList[viewModel.selectedTabIndex.value].pageType,
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No files opened")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FileTabItem(
    viewModel: EditorTabViewModel = viewModel(),
    index: Int,
    tab: EditorTabContent,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    Tab(
        selected = viewModel.selectedTabIndex.value == index,
        onClick = {
            viewModel.onTabSelected(
                scope = scope,
                index = index,
                pagerState = pagerState
            )
        },
        modifier = Modifier.height(45.dp),
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.outline,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Icon(
                imageVector = fileIcon(file = tab.file),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = tab.file.name)
        }
    }
}