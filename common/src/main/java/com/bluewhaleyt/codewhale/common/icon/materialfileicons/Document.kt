package com.bluewhaleyt.codewhale.common.icon.materialfileicons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.bluewhaleyt.codewhale.common.icon.MaterialFileIcons

public val MaterialFileIcons.Document: ImageVector
    get() {
        if (_document != null) {
            return _document!!
        }
        _document = Builder(name = "Document", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF42a5f5)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(13.0f, 9.0f)
                horizontalLineToRelative(5.5f)
                lineTo(13.0f, 3.5f)
                verticalLineTo(9.0f)
                moveTo(6.0f, 2.0f)
                horizontalLineToRelative(8.0f)
                lineToRelative(6.0f, 6.0f)
                verticalLineToRelative(12.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, true, -2.0f, 2.0f)
                horizontalLineTo(6.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, false, true, -2.0f, -2.0f)
                verticalLineTo(4.0f)
                curveToRelative(0.0f, -1.11f, 0.89f, -2.0f, 2.0f, -2.0f)
                moveToRelative(9.0f, 16.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineTo(6.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(9.0f)
                moveToRelative(3.0f, -4.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineTo(6.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(12.0f)
                close()
            }
        }
        .build()
        return _document!!
    }

private var _document: ImageVector? = null
