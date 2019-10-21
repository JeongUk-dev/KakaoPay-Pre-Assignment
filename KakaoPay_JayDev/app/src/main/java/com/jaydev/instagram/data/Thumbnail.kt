package com.jaydev.instagram.data

import java.io.Serializable

data class Thumbnail(
	val width: Int?,
	val height: Int?,
	val url: String?
) : Serializable
