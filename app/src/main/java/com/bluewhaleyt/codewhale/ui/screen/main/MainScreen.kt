@file:OptIn(ExperimentalFoundationApi::class)

package com.bluewhaleyt.codewhale.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.ui.screen.main.pages.PermissionPage
import com.bluewhaleyt.codewhale.ui.screen.main.pages.PrivacyPolicyPage

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {
    val pagerState = rememberPagerState(
        pageCount = { viewModel.pageCount.value }
    )

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> PrivacyPolicyPage(pagerState = pagerState)
            1 -> PermissionPage(pagerState = pagerState, navController = navController)
        }
    }

}