package com.msib.accountingproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.msib.accountingproject.databinding.ActivityResultBinding
import com.msib.accountingproject.model.CashModel
import com.msib.accountingproject.viewmodel.CountViewModel

class ResultActivity : AppCompatActivity() {

    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding

    private lateinit var countViewModel: CountViewModel
    private var tableAdapter = TableAdapter()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // hide action bar
        supportActionBar?.hide()

        val cashModel = intent.getParcelableExtra<CashModel>("list_cash")
        binding?.columnInvestment?.text = cashModel?.investment.toString()
        binding?.columnInterestRate?.text = cashModel?.interestRate.toString()

        binding?.ivBack?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        countViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CountViewModel::class.java)

        when {
            cashModel?.PP == true -> {
                countViewModel.setPBP(cashModel.investment, cashModel.period, cashModel.cash)
                countViewModel.getPBP().observe(this) { result ->
                    if (result != null) {
                        binding?.columnPaybackPeriod?.text = "${String.format("%.2f",  result.paybackPeriod)} tahun"
                        binding?.rowInterestRate?.visibility = View.GONE
                        binding?.rowIrr?.visibility = View.GONE
                        binding?.rowNpv?.visibility = View.GONE
                        binding?.tvKetNpv?.text = result.message
                    }
                }
            }
            cashModel?.NPV == true -> {
                countViewModel.setNPV(cashModel.investment, cashModel.period, cashModel.cash, cashModel.interestRate)
                countViewModel.getNPV().observe(this) { result ->
                    if (result != null) {
                        binding?.columnNpv?.text = String.format("%.2f", result.NPV)
                        binding?.tvKetNpv?.text = "-${result.message}"
                        binding?.rowIrr?.visibility = View.GONE
                        binding?.rowPaybackPeriod?.visibility = View.GONE
                    }
                }
            }
            cashModel?.newIRR == true -> {
                countViewModel.setIRR(cashModel.investment, cashModel.cash, cashModel.interestRate, cashModel.firstRate, cashModel.SecondRate)
                countViewModel.getIRR().observe(this) { result ->
                    if (result != null) {
                        binding?.columnIrr?.text = String.format("%.2f", result.IRR)
                        binding?.tvKetNpv?.text = "-${result.message}"
                        binding?.rowPaybackPeriod?.visibility = View.GONE
                        binding?.rowNpv?.visibility = View.GONE
                    }
                }
            }
            cashModel?.All == true -> {
                countViewModel.setPBP(cashModel.investment, cashModel.period, cashModel.cash)
                countViewModel.getPBP().observe(this) { result ->
                    if (result != null) {
                        binding?.columnPaybackPeriod?.text = "${String.format("%.2f",  result.paybackPeriod)} tahun"
                    }
                }

                countViewModel.setNPV(cashModel.investment, cashModel.period, cashModel.cash, cashModel.interestRate)
                countViewModel.getNPV().observe(this) { result ->
                    if (result != null) {
                        binding?.columnNpv?.text = String.format("%.2f", result.NPV)
                        binding?.tvKetNpv?.text = "-${result.message}"
                    }
                }

                countViewModel.setIRR(cashModel.investment, cashModel.cash, cashModel.interestRate, cashModel.firstRate, cashModel.SecondRate)
                countViewModel.getIRR().observe(this) { result ->
                    if (result != null) {
                        binding?.columnIrr?.text = String.format("%.2f", result.IRR)
                        binding?.tvKetNpv?.text = "-${result.message}"
                    }
                }
            }
        }

        tableAdapter.setData(cashModel?.cash)
        with(binding?.tableRecyclerView) {
            this?.layoutManager = LinearLayoutManager(this@ResultActivity)
            this?.adapter = tableAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}