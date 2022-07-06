package com.msib.accountingproject.network

import com.msib.accountingproject.request.RequestIRR
import com.msib.accountingproject.request.RequestNPV
import com.msib.accountingproject.request.RequestPP
import com.msib.accountingproject.response.IRRResponse
import com.msib.accountingproject.response.NPVResponse
import com.msib.accountingproject.response.PBPResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("v1/PP")
    fun PostPP(@Body requestPP : RequestPP) : Call<PBPResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/NPV")
    fun PostNPV(@Body requestNPV: RequestNPV) : Call<NPVResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/IRR")
    fun PostIRR(@Body requestIRR: RequestIRR) : Call<IRRResponse>
}