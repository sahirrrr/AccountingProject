package com.msib.accountingproject.request

data class RequestNPV(
    var investment: Int? = null,
    var period: Int? = null,
    var cashflows: ArrayList<Int>? = null,
    var interestrate: Float? = null
)
