package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Caption(
	val id: String?,
	val text: String?,
	@SerializedName("created_time")
	val createdTime: String?,
	@SerializedName("from")
	val from: From?
) : Serializable
