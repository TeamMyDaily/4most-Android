package org.mydaily.util.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}

fun Fragment.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment) {
    requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}

fun Fragment.replaceAndAddBackStack(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment, name: String) {
    requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment)
        .addToBackStack(name)
        .commit()
}

fun Fragment.popBackStack() {
    requireActivity().supportFragmentManager
        .popBackStack()
}

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.shortToast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
}