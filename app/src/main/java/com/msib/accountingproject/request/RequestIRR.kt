package com.msib.accountingproject.request

data class RequestIRR(
    var investment: Int? = null,
    var cashflows : List<Int>? = null,
    var baseline: Float? = null,
    var firstRate: Float? = null,
    var SecondRate: Float? = null
)