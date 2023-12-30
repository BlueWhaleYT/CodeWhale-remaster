package com.bluewhaleyt.codewhale.viewmodel

import android.Manifest
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    fun onLauncherResult(
        permissions: Map<String, Boolean>
    ) {

    }

}