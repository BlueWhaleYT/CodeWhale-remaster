package com.bluewhaleyt.codewhale.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bluewhaleyt.codewhale.App
import com.bluewhaleyt.codewhale.ui.theme.CodeWhaleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeWhaleTheme {
                App()
            }
        }
    }
}