package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName

data class Meta(
	val code: Int?,
	@SerializedName("error_type")
	val errorType: String?,
	@SerializedName("error_message")
	val errorMessage: String?
)
