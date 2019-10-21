package com.jaydev.instagram.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

abstract class Content {
	abstract val lowResolution: LowResolution?
	abstract val standardResolution: StandardResolution?
}


data class Images(
	val thumbnail: Thumbnail?,
	@SerializedName("low_resolution")
	override val lowResolution: LowResolution?,
	@SerializedName("standard_resolution")
	override val standardResolution: StandardResolution?
) : Content(), Serializable

data class Videos(
	@SerializedName("low_bandwidth")
	val lowBandwidth: LowBandwidth?,
	@SerializedName("low_resolution")
	override val lowResolution: LowResolution?,
	@SerializedName("standard_resolution")
	override val standardResolution: StandardResolution?
) : Content(), Serializable