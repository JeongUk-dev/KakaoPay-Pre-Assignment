package com.jaydev.instagram.util.ext

import android.content.Context
import android.widget.Toast

fun Context.dpToPixel(dp: Float): Float {
	return resources.displayMetrics.density * dp
}

fun Context.pixelToDp(pixel: Float): Float {
	return pixel / resources.displayMetrics.density
}

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()