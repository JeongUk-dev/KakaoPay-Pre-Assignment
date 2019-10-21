package com.jaydev.instagram.common

import android.content.Context
import android.content.SharedPreferences
import com.jaydev.instagram.util.ext.clear
import com.jaydev.instagram.util.ext.putValue

class UserInfoManager private constructor() {

	private val SHARE_PREF_USER_INFO = "userInfo"

	private lateinit var sharedPreferences: SharedPreferences

	fun init(context: Context) {
		sharedPreferences = context.getSharedPreferences(SHARE_PREF_USER_INFO, Context.MODE_PRIVATE)
	}

	fun clear() {
		sharedPreferences.clear()
	}

	var userId: String
		get() = sharedPreferences.getString("userId", string_default)!!
		set(value) {
			sharedPreferences.putValue("userId", value)
		}

	var userName: String
		get() = sharedPreferences.getString("userName", string_default)!!
		set(value) {
			sharedPreferences.putValue("userName", value)
		}

	var profileImgUrl: String
		get() = sharedPreferences.getString("profileImgUrl", string_default)!!
		set(value) {
			sharedPreferences.putValue("profileImgUrl", value)
		}

	var userPassword: String
		get() = sharedPreferences.getString("userPassword", string_default)!!
		set(value) {
			sharedPreferences.putValue("userPassword", value)
		}

	var accessToken: String
		get() = sharedPreferences.getString("accessToken", string_default)!!
		set(value) {
			sharedPreferences.putValue("accessToken", value)
		}

	companion object {
		private var instance: UserInfoManager? = null

		@JvmStatic
		fun getInstance() = instance ?: synchronized(this) {
			instance ?: UserInfoManager().also { instance = it }
		}
	}
}