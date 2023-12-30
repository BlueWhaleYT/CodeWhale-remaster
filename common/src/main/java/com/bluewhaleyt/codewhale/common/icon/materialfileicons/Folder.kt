package com.bluewhaleyt.codewhale.common.icon.materialfileicons

import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import com.bluewhaleyt.codewhale.common.icon.MaterialFileIcons

@Composable
public fun MaterialFileIcons.Folder(colorScheme: ColorScheme): ImageVector
     {
        if (_folder != null) {
            return _folder!!
        }
        _folder = materialIcon(name = "Folder") {
            path(
                fill = SolidColor(colorScheme.primary)
            ) {
                moveTo(10.0f, 4.0f)
                horizontalLineTo(4.0f)
                curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f)
                lineTo(2.0f, 18.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(16.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(8.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                horizontalLineToRelative(-8.0f)
                lineToRelative(-2.0f, -2.0f)
                close()
            }
        }
        return _folder!!
    }

private var _folder: ImageVector? = null