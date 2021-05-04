package com.example.acrominesample.models

import com.google.gson.annotations.SerializedName

data class LfsItem(

	@field:SerializedName("freq")
	val freq: Int? = null,

	@field:SerializedName("lf")
	val lf: String? = null,

	@field:SerializedName("vars")
	val vars: List<LfsItem?>? = null,

	@field:SerializedName("since")
	val since: Int? = null
)

data class ResponseItem(

	@field:SerializedName("sf")
	val sf: String? = null,


	@field:SerializedName("lfs")
	val lfs: List<LfsItem?>? = null
)
