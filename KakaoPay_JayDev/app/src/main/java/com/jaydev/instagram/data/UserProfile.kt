package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class UserProfile(
	val id: String?,
	val username: String?,
	@SerializedName("profile_picture")
	val profilePicture: String?,
	@SerializedName("full_name")
	val fullName: String?,
	val bio: String?,
	val website: String?,
	@SerializedName("is_business")
	val isBusiness: Boolean?,
	val counts: Counts?
) : Serializable