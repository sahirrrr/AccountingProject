package com.msib.accountingproject.request

data class RequestNPV(
    var investment: Int,
    var period: Int,
    var cashflows : List<Int>,
    var interestrate: Float
)
