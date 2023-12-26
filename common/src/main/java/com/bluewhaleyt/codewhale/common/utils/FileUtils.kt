package com.bluewhaleyt.codewhale.common.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import androidx.core.content.ContextCompat

class FileUtils(
    private val context: Context
) {

    fun isPermissionsGranted(permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun isExternalStorageManager() = if (isSdkAtLeast(30)) Environment.isExternalStorageManager()
        else false

    fun isExternalStorageGranted() = if (isSdkAtLeast(30)) isExternalStorageManager()
        else isPermissionsGranted(arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        ))

    fun requestAllFileAccess(withNavigate: Boolean = true) {
        if (isSdkAtLeast(30)) {
            if (!isExternalStorageGranted()) {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                if (withNavigate) {
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.setData(uri)
                }
                context.startActivity(intent)
            }
        }
    }

}