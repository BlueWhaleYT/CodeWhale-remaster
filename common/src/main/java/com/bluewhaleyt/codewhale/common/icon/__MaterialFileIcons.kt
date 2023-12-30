package com.bluewhaleyt.codewhale.common.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Document
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Image
import com.bluewhaleyt.codewhale.common.icon.materialfileicons.Java
import kotlin.collections.List as ____KtList

public object MaterialFileIcons

private var __AllIcons: ____KtList<ImageVector>? = null

public val MaterialFileIcons.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Java, Document, Image)
    return __AllIcons!!
  }
