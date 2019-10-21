package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Pagination(
	@SerializedName("next_url")
	val nextUrl: String?,
	@SerializedName("next_max_id")
	val nextMaxId: Int?
) : Serializable
