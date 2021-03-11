package org.mydaily.util.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import org.mydaily.R
import org.mydaily.util.Event

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

fun Context.setupToast(
    lifecycleOwner: LifecycleOwner,
    toastEvent: LiveData<Event<String>>
) {
    toastEvent.observe(lifecycleOwner, { event ->
        event.getContentIfNotHandled()?.let {
            shortToast(it)
        }
    })
}

fun Fragment.createKeywordChip(str: String, listener: (it: Chip) -> (Unit)): Chip {
    val chipDrawable = ChipDrawable.createFromAttributes(
        requireContext(),
        null,
        0,
        R.style.Widget_MaterialComponents_Chip_Choice
    )
    return Chip(requireContext()).apply {
        text = str
        setChipDrawable(chipDrawable)
        setChipBackgroundColorResource(R.color.selector_chip)
        setTextAppearance(R.style.MyDailyChipTextStyleAppearance)
        setRippleColorResource(android.R.color.transparent)
        setOnClickListener {
            listener.invoke(it as Chip)
        }
    }
}