package com.bluewhaleyt.codewhale.ui.screen.main

import android.Manifest
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DriveFileMoveRtl
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.bluewhaleyt.codewhale.Screen
import com.bluewhaleyt.codewhale.common.ui.getNextPage
import com.bluewhaleyt.codewhale.common.ui.getPreviousPage
import com.bluewhaleyt.codewhale.common.utils.FileUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _pageCount = mutableStateOf(2)
    val pageCount: State<Int> = _pageCount
    val pagerAnimation: AnimationSpec<Float> = spring(stiffness = Spring.StiffnessMediumLow)

    // PrivacyPolicyPage
    private val _privacyPolicyFile = mutableStateOf("privacy-policy.md")
    val privacyPolicyFile: State<String> = this._privacyPolicyFile

    fun getPrivacyPolicyFile(context: Context): String {
        return context.assets.open(privacyPolicyFile.value)
            .bufferedReader().use { it.readText() }
            .replace("\$APP_NAME$", "CodeWhale")
            .replace("\$COMPANY_NAME$", "BlueWhaleYT")
            .replace("\$DATE$", "Dec 24, 2023")
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun onContinueButtonClick(
        scope: CoroutineScope,
        pagerState: PagerState
    ) {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.getNextPage(), animationSpec = pagerAnimation)
        }
    }

    // PermissionPage
    @OptIn(ExperimentalFoundationApi::class)
    fun onBackButtonClick(
        scope: CoroutineScope,
        pagerState: PagerState
    ) {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.getPreviousPage(), animationSpec = pagerAnimation)
        }
    }

    private val _permissionCheckBoxChecked = mutableStateOf(false)
    val permissionCheckBoxChecked: MutableState<Boolean> = _permissionCheckBoxChecked

    fun onPermissionCheckBoxChecked(checked: Boolean) {
        permissionCheckBoxChecked.value = checked
    }

    val permissionContent = mapOf(
        Icons.Outlined.Visibility to "Read files from the external Storage",
        Icons.Outlined.Edit to "Write files from the external Storage",
        Icons.Outlined.DriveFileMoveRtl to "All Files Access"
    )

    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    @OptIn(ExperimentalFoundationApi::class)
    fun onGrantPermissionButtonClick(
        scope: CoroutineScope,
        pagerState: PagerState,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>
    ) {
        launcher.launch(permissions)
    }

    fun onGrantPermissionResult(
        context: Context,
        navController: NavController
    ) {
        val fileUtils = FileUtils(context)
        if (fileUtils.isPermissionsGranted(permissions)) {
            fileUtils.requestAllFileAccess()
            navController.navigate(Screen.EditorScreen.route)
        }
    }

}