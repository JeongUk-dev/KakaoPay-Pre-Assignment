package com.jaydev.instagram.data

data class PostsResponse(
	val pagination: Pagination?,
	val data: List<Post>?,
	val meta: Meta?
)
