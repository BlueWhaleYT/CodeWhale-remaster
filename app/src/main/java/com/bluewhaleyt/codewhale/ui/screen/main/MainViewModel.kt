package com.bluewhaleyt.codewhale.ui.screen.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val pageCount = 2

    fun fromPrivacyPolicyPage() = PrivacyPolicyPage

    fun fromPermissionPage() = PermissionPage

    object PrivacyPolicyPage {
        val file = "privacy-policy.md"
    }

    object PermissionPage {

    }

}