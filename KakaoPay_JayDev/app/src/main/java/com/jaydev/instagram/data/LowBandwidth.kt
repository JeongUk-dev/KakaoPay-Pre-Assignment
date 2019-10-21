package com.jaydev.instagram.data

import java.io.Serializable

data class LowBandwidth(
	val id: Int?,
	val width: Int?,
	val height: Int?,
	val url: String?
) : Serializable