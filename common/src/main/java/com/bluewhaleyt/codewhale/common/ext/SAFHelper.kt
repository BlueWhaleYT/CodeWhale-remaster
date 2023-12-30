package com.bluewhaleyt.codewhale.common.ext

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import com.bluewhaleyt.codewhale.common.utils.file.UriResolver

class SAFHelper(
    override val context: Context,
    val uri: Uri
) : FileHelper(context)

fun Context.getSAFHelper(uri: Uri) = SAFHelper(this, uri)

fun SAFHelper.toDocumentedUri() = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri))

fun SAFHelper.toRealFilePath(
    fromDocumentTree: Boolean
): String {
    val uri = if (fromDocumentTree) toDocumentedUri() else uri
    return UriResolver.getPathFromUri(context, uri).toString()
}