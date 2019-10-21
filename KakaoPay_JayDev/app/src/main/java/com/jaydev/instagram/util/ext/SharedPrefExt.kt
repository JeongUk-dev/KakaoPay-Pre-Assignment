package com.jaydev.instagram.util.ext

import android.content.SharedPreferences

fun <T> SharedPreferences.putValue(key: String, value: T?) {
	edit().apply {
		when (value) {
			is String -> putString(key, value)
			is Int -> putInt(key, value)
			is Float -> putFloat(key, value)
			is Boolean -> putBoolean(key, value)
			is Long -> putLong(key, value)
		}
	}.apply()
}

fun SharedPreferences.remove(key: String) {
	edit().remove(key).apply()
}

fun SharedPreferences.clear() {
	edit().clear().apply()
}