package com.msib.accountingproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.msib.accountingproject.databinding.ActivityResultBinding
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
        binding?.rowInvestment?.text = cashModel?.investment.toString()
        binding?.rowInterestRate?.text = cashModel?.interestRate.toString()

        countViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CountViewModel::class.java)

        countViewModel.setPBP(cashModel?.investment!!, cashModel.period!!, cashModel.cash!!)
        countViewModel.getPBP().observe(this) { result ->
            if (result != null) {
                binding?.rowPaybackPeriod?.text = result.paybackPeriod.toString()
            }
        }

        countViewModel.setNPV(cashModel.investment!!, cashModel.period!!, cashModel.cash!!, cashModel.interestRate!!)
        countViewModel.getNPV().observe(this) { result ->
            if (result != null) {
                binding?.rowNpv?.text = String.format("%.2f", result.NPV)
                binding?.tvKetNpv?.text = "-${result.message}"
            }
        }

        countViewModel.setIRR(cashModel.investment!!, cashModel.cash!!, cashModel.interestRate!!, cashModel.firstRate!!, cashModel.SecondRate!!)
        countViewModel.getIRR().observe(this) { result ->
            if (result != null) {
                binding?.rowIrr?.text = String.format("%.2f", result.IRR)
                binding?.tvKetIrr?.text = "-${result.message}"
            }
        }

        tableAdapter.setData(cashModel.cash)
        with(binding?.tableRecyclerView) {
            this?.layoutManager = LinearLayoutManager(this@ResultActivity)
            this?.adapter = tableAdapter
        }
    }
}