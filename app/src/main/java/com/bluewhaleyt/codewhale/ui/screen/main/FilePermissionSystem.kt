package com.bluewhaleyt.codewhale.ui.screen.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

internal interface FilePermissionSystemInterface {
    fun onPermissionsResult(context: Context, permissions: Map<String, @JvmSuppressWildcards Boolean>)
    fun arePermissionsGranted(context: Context): Boolean
}

object FilePermissionSystem : FilePermissionSystemInterface {

    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    override fun arePermissionsGranted(context: Context): Boolean {
        val readPermission = ContextCompat.checkSelfPermission(context, permissions[0])
        val writePermission = ContextCompat.checkSelfPermission(context, permissions[1])
        return (readPermission == PackageManager.PERMISSION_GRANTED || writePermission == PackageManager.PERMISSION_GRANTED)
    }

    override fun onPermissionsResult(context: Context, permissions: Map<String, @JvmSuppressWildcards Boolean>) {
        val readGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false
        val writeGranted = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: false

        if (!readGranted && !writeGranted) {
            Log.e("FilePermissionSystem", "Read & Write are denied")
        } else if (!readGranted) {
            Log.e("FilePermissionSystem", "Read is denied")
        } else if (!writeGranted) {
            Log.e("FilePermissionSystem", "Write is denied")
        }
    }

    @Composable
    operator fun invoke(
        action: (
            launcher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
                ) -> Unit,
        onResult: ((Map<String, @JvmSuppressWildcards Boolean>) -> Unit)? = null
    ) {
        val context = LocalContext.current
        val permissionGranted = arePermissionsGranted(context)

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { permissions ->
                onPermissionsResult(
                    context = context,
                    permissions = permissions
                )
//                onResult?.invoke(permissions)
                Log.w("File permission", "onResult launched")
            }
        )
        LaunchedEffect(Unit) {
            action(launcher)
        }
    }

}