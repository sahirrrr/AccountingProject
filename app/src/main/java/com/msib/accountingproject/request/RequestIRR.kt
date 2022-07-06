package com.msib.accountingproject.request

data class RequestIRR(
    var investment: Int,
    var cashflows : List<Int>,
    var baseline: Float,
    var firstRate: Float,
    var SecondRate: Float
)