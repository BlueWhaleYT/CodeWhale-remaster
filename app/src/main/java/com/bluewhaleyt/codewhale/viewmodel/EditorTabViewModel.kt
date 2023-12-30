package com.bluewhaleyt.codewhale.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bluewhaleyt.codewhale.common.utils.file.FileTypes
import com.bluewhaleyt.codewhale.common.utils.logD
import com.bluewhaleyt.codewhale.ui.screen.editor.page.EditorFileTabPageType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

data class EditorTabContent(
    val file: File,
    val pageType: EditorFileTabPageType = EditorFileTabPageType.Default
)

class EditorTabViewModel : ViewModel() {

    private val _selectedTabIndex = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    private val _tabList = mutableStateListOf<EditorTabContent>()
    val tabList: SnapshotStateList<EditorTabContent> = _tabList

    @OptIn(ExperimentalFoundationApi::class)
    fun onTabSelected(
        scope: CoroutineScope,
        index: Int,
        pagerState: PagerState
    ) {
        _selectedTabIndex.value = index
        scope.launch {
            pagerState.scrollToPage(index)
        }
    }

    fun addTab(
        file: File,
    ) {
        val pageType = when (file.extension) {
            in FileTypes.BitmapImage.extensions -> EditorFileTabPageType.ImagePreview
            else -> EditorFileTabPageType.Default
        }
        val isFileTabOpened = _tabList.any() { it.file.path == file.path }
        if (isFileTabOpened) {
            this logD "Already opened file: ${file.path}"
        } else {
            _tabList.add(
                EditorTabContent(
                    file = file,
                    pageType = pageType
                )
            )
        }
        this logD "Tab list size: ${_tabList.size}"
    }

}