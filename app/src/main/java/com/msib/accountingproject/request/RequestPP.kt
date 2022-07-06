package com.msib.accountingproject.request

data class RequestPP(
    var investment: Int,
    var period: Int,
    var cashflows: ArrayList<Int>
)
