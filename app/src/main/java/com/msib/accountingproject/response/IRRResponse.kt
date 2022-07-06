package com.msib.accountingproject.response

import com.google.gson.annotations.SerializedName

data class IRRResponse(

	@field:SerializedName("IRR")
	val IRR: Double? = null,

	@field:SerializedName("positive")
	val positive: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
