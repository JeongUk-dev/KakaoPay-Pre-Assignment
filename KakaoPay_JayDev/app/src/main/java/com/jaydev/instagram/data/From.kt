package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class From(
	val id: String?,
	@SerializedName("full_name")
	val fullName: String?,
	@SerializedName("profile_picture")
	val profilePicture: String?,
	val username: String?
) : Serializable