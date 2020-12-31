package org.mydaily.util.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}

fun shortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun shortToast(context: Context, resourceId: Int) {
    Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show()
}

fun longToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun longToast(context: Context, resourceId: Int) {
    Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show()
}