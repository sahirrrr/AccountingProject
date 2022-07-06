package com.msib.accountingproject.response

import com.google.gson.annotations.SerializedName

data class NPVResponse(

	@field:SerializedName("NPV")
	val NPV: Double? = null,

	@field:SerializedName("positive")
	val positive: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
