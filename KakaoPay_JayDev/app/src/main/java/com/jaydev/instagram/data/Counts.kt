package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Counts(
	val media: Int?,
	val follows: Int?,
	@SerializedName("followed_by")
	val followedBy: Int?
) : Serializable