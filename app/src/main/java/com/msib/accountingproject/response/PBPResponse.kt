package com.msib.accountingproject.response

import com.google.gson.annotations.SerializedName

data class PBPResponse(

	@field:SerializedName("cashBeforePeriod")
	val cashBeforePeriod: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("paybackPeriod")
	val paybackPeriod: Double? = null,

	@field:SerializedName("accumulatedCashflow")
	val accumulatedCashflow: Int? = null
)
