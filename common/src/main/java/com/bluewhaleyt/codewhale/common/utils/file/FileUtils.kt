package com.bluewhaleyt.codewhale.common.utils.file

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.bluewhaleyt.codewhale.common.utils.isSdkAtLeast
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FileUtils(
    private val context: Context
) {

    fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun arePermissionsGranted(permissions: Array<String>): Boolean {
        return permissions.all { isPermissionGranted(it) }
    }

    fun isExternalStorageManager() = if (isSdkAtLeast(30)) Environment.isExternalStorageManager()
        else false

    fun isExternalStorageGranted() = if (isSdkAtLeast(30)) isExternalStorageManager()
        else arePermissionsGranted(arrayOf(
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

fun String.getFileName() = this.substring(this.lastIndexOf("/") + 1)

fun String.getFileContent(
    wordWrap: Boolean = true
): String {
    val reader = BufferedReader(FileReader(this))
    return if (wordWrap) reader.readLines().joinToString("\n")
    else reader.readLines().toString()
}

fun Uri.toDocumentedUri(): Uri {
    return DocumentsContract.buildDocumentUriUsingTree(this, DocumentsContract.getTreeDocumentId(this))
}

fun Uri.toRealFilePath(context: Context, fromDocumentTree: Boolean = false): String {
    val uri = if (fromDocumentTree) this.toDocumentedUri() else this
    return UriResolver.getPathFromUri(context, uri).toString()
}

fun String.isDirectory() = File(this).isDirectory

fun String.getFileIcon(): Any {
    val url = "https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/main/icons"
    val fileExtension = File(this).extension

    return if (this.isDirectory()) "$url/folder-other.svg"
    else when (fileExtension) {
        in FileTypes.BitmapImage.extensions -> "$url/image.svg"
        in FileTypes.VectorImage.extensions -> "$url/svg.svg"
        in FileTypes.Markdown.extensions -> "$url/markdown.svg"
        in FileTypes.Markup.extensions -> "$url/html.svg"
        in FileTypes.JavaScript.extensions -> "$url/javascript.svg"
        in FileTypes.TypeScript.extensions -> "$url/typescript.svg"
        else -> "$url/$fileExtension.svg"
    }

}