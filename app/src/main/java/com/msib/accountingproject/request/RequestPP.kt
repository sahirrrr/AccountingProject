package com.msib.accountingproject.request

data class RequestPP(
    var investment: Int? = null,
    var period: Int? = null,
    var cashflows: ArrayList<Int>? = null
)
