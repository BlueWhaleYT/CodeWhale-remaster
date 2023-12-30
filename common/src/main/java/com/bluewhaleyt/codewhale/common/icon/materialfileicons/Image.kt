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

public val MaterialFileIcons.Image: ImageVector
    get() {
        if (_image != null) {
            return _image!!
        }
        _image = Builder(name = "Image", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF26a69a)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.976f, 9.072f)
                horizontalLineToRelative(5.368f)
                lineToRelative(-5.368f, -5.367f)
                verticalLineToRelative(5.367f)
                moveTo(6.144f, 2.241f)
                horizontalLineToRelative(7.808f)
                lineToRelative(5.856f, 5.855f)
                verticalLineToRelative(11.711f)
                arcToRelative(1.952f, 1.952f, 0.0f, false, true, -1.952f, 1.952f)
                horizontalLineTo(6.145f)
                arcToRelative(1.951f, 1.951f, 0.0f, false, true, -1.952f, -1.952f)
                verticalLineTo(4.192f)
                curveToRelative(0.0f, -1.083f, 0.868f, -1.951f, 1.952f, -1.951f)
                moveToRelative(0.0f, 17.567f)
                horizontalLineToRelative(11.71f)
                verticalLineTo(12.0f)
                lineToRelative(-3.903f, 3.904f)
                lineTo(12.0f, 13.952f)
                lineToRelative(-5.856f, 5.856f)
                moveTo(8.096f, 9.073f)
                arcToRelative(1.952f, 1.952f, 0.0f, false, false, -1.952f, 1.952f)
                arcToRelative(1.952f, 1.952f, 0.0f, false, false, 1.952f, 1.951f)
                arcToRelative(1.952f, 1.952f, 0.0f, false, false, 1.952f, -1.951f)
                arcToRelative(1.952f, 1.952f, 0.0f, false, false, -1.952f, -1.952f)
                close()
            }
        }
        .build()
        return _image!!
    }

private var _image: ImageVector? = null
