package com.msib.accountingproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CashModel(
    var cash: ArrayList<Int>? = null,
    var period: Int? = null,
    var investment: Int? = null,
    var interestRate: Float? = null,
    var firstRate: Float? = null,
    var SecondRate: Float? = null,
    var PP: Boolean? = false,
    var NPV: Boolean? = false,
    var IRR: Boolean? = false,
    var newIRR: Boolean? = false,
    var All: Boolean? = false,
) : Parcelable
