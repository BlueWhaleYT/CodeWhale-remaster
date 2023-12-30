package com.bluewhaleyt.codewhale.ui.screen.editor.page

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

@Composable
fun EditorImagePreviewPage(
    file: File
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = BitmapFactory.decodeFile(file.path).asImageBitmap()

        Image(
            bitmap = bitmap,
            contentDescription = null
        )
    }
}