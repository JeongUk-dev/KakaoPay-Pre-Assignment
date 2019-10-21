package com.jaydev.instagram.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Post(
	val id: String?,
	val user: User?,
	@Expose
	var images: Images?,
	@Expose
	var videos: Videos?,
	@SerializedName("created_time")
	val createdTime: String?,
	val caption: Caption?,
	@SerializedName("user_has_liked")
	val userHasLiked: Boolean?,
	val likes: Likes?,
	val tags: List<Any>?,
	val filter: String?,
	val comments: Comments?,
	val type: PostType?,
	val link: String?,
	val location: Location?,
	val attribution: Any?,
	@SerializedName("users_in_photo")
	val usersInPhoto: List<Any>?
) : Serializable {
	enum class PostType {
		@SerializedName("video")
		VIDEO,
		@SerializedName("image")
		IMAGE
	}
}