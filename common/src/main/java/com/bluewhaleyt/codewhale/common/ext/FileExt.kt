package com.bluewhaleyt.codewhale.common.ext

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.bluewhaleyt.codewhale.common.utils.isSdkAtLeast

open class FileHelper internal constructor(
    open val context: Context
)

fun Context.getFileHelper() = FileHelper(this)

fun FileHelper.externalStoragePath() = Environment.getExternalStorageDirectory().path

fun FileHelper.isPermissionGranted(
    permission: String
) = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

fun FileHelper.arePermissionsGranted(
    permissions: Array<String>
) = permissions.all {
    isPermissionGranted(it)
}

fun FileHelper.isExternalStorageManager() = if (isSdkAtLeast(30)) Environment.isExternalStorageManager()
    else false

fun FileHelper.isExternalStorageGranted() = if (isSdkAtLeast(30)) isExternalStorageManager()
    else arePermissionsGranted(
        permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

fun FileHelper.requestAllFileAccess(
    autoNavigate: Boolean = true
) {
    if (isSdkAtLeast(30)) {
        if (!isExternalStorageGranted()) {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            if (autoNavigate) {
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.setData(uri)
            }
            context.startActivity(intent)
        }
    }
}