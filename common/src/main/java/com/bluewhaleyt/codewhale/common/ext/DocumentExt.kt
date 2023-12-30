package com.bluewhaleyt.codewhale.common.ext

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class DocumentHelper internal constructor(
    override val context: Context,
    val file: File
) : FileHelper(context)

fun FileHelper.getDocument(file: File) = DocumentHelper(context, file)

fun DocumentHelper.toFile(filePath: String) = File(filePath)

fun DocumentHelper.getFileName(): String = toFile(file.path).name

fun DocumentHelper.getFileExtension(): String = toFile(file.path).extension

fun DocumentHelper.getFileContent(
    wordWrap: Boolean = true
): String {
    val reader = BufferedReader(FileReader(toFile(file.path)))
    return if (wordWrap) reader.readLines().joinToString("\n")
        else reader.readLines().toString()
}