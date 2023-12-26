package com.bluewhaleyt.codewhale.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState

@OptIn(ExperimentalFoundationApi::class)
suspend fun PagerState.refreshCurrentPage() {
    scrollToPage(getPreviousPage())
    scrollToPage(getNextPage())
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.getPreviousPage() = this.currentPage - 1

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.getNextPage() = this.currentPage + 1
