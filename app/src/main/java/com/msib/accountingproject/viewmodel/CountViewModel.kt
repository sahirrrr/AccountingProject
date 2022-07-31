package com.msib.accountingproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msib.accountingproject.network.ApiConfig
import com.msib.accountingproject.request.RequestIRR
import com.msib.accountingproject.request.RequestNPV
import com.msib.accountingproject.request.RequestPP
import com.msib.accountingproject.response.IRRResponse
import com.msib.accountingproject.response.NPVResponse
import com.msib.accountingproject.response.PBPResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountViewModel: ViewModel() {
    private val NPVResult = MutableLiveData<NPVResponse>()
    private val PBPResult = MutableLiveData<PBPResponse>()
    private val IRRResult = MutableLiveData<IRRResponse>()

    private val TAG = CountViewModel::class.java.simpleName

    fun getNPV() : LiveData<NPVResponse> = NPVResult

    fun setNPV(investment: Int?, period: Int?, cash: ArrayList<Int>?, interestRate: Float?) {
        val request = RequestNPV(investment, period, cash, interestRate)

        val client = ApiConfig.getApiService().PostNPV(request)

        client.enqueue(object : Callback<NPVResponse> {
            override fun onResponse(call: Call<NPVResponse>, response: Response<NPVResponse>) {
                if (response.isSuccessful) {
                    NPVResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<NPVResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPBP(): LiveData<PBPResponse> = PBPResult

    fun setPBP(investment: Int?, period: Int?, cashflows: List<Int>?) {
        val request = RequestPP(investment, period, cashflows as ArrayList<Int>)
        val client = ApiConfig.getApiService().PostPP(request)

        client.enqueue(object : Callback<PBPResponse> {
            override fun onResponse(call: Call<PBPResponse>, response: Response<PBPResponse>) {
                if (response.isSuccessful) {
                    PBPResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<PBPResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getIRR(): LiveData<IRRResponse> = IRRResult

    fun setIRR(
        investment: Int?,
        cash: ArrayList<Int>?,
        baseline: Float?,
        firstRate: Float?,
        secondRate: Float?
    ) {
        val request = RequestIRR(investment, cash, baseline, firstRate, secondRate)

        val client = ApiConfig.getApiService().PostIRR(request)

        client.enqueue(object : Callback<IRRResponse> {
            override fun onResponse(call: Call<IRRResponse>, response: Response<IRRResponse>) {
                if (response.isSuccessful) {
                    IRRResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<IRRResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}