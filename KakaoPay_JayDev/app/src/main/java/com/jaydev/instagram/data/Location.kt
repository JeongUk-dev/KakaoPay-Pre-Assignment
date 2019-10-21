package com.jaydev.instagram.data

import java.io.Serializable

data class Location(
	val id: Int?,
	val name: String?,
	val latitude: Double?,
	val longitude: Double?
) : Serializable