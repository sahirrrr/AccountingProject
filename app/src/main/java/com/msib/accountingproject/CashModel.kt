package com.msib.accountingproject

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
) : Parcelable
