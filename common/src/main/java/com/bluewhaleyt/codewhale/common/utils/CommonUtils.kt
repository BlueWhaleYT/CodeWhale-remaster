package com.bluewhaleyt.codewhale.common.utils

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.ChecksSdkIntAtLeast

//
//@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
//fun <T> invokeIfSdkIntAtLeast(
//    sdkVersion: T,
//    onElseInvoke: (() -> Unit)? = null,
//    onInvoke: () -> Unit,
//) {
//    when (sdkVersion) {
//        is Int -> {
//            if (Build.VERSION.SDK_INT >= sdkVersion) onInvoke() else onElseInvoke?.invoke()
//        }
//        is Array<*> -> {
//            if (sdkVersion.all { Build.VERSION.SDK_INT >= it.toString().toInt() }) {
//                onInvoke()
//            } else {
//                onElseInvoke?.invoke()
//            }
//        }
//    }
//
//}

@ChecksSdkIntAtLeast(parameter = 0)
fun isSdkAtLeast(sdkVersion: Int) = Build.VERSION.SDK_INT >= sdkVersion

fun Context.toast(str: String) = Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

infix fun Any.logD(str: String) = when (this) {
    is String -> Log.d(this, str)
    else -> Log.d(this::class.java.simpleName, str)
}