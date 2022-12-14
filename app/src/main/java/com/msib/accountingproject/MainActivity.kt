package com.msib.accountingproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.msib.accountingproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val arr = ArrayList<Int>()
    private val cashModel = CashModel()
    var n = 1

    var layoutList: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // hide action bar
        supportActionBar?.hide()

        layoutList = binding?.layoutList

        binding?.buttonAdd?.setOnClickListener(this)
        binding?.btnSubmit?.setOnClickListener(this)

        addView()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_add -> {
                addView()
            }
            R.id.btn_submit -> {
                if (checkIfValidAndRead()) {
                    Log.d("list_cash", cashModel.toString())
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra("list_cash", cashModel)
                    startActivity(intent)

                    arr.clear()
                } else {
                    Toast.makeText(this,"something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkIfValidAndRead(): Boolean {

        var result = true

        for (i in 0 until layoutList?.childCount!!) {
            val layoutView = layoutList?.getChildAt(i)
            val edtYear = layoutView?.findViewById<TextInputEditText>(R.id.edt_cash_flow)

            if (edtYear?.text.toString() != "") {
                arr.add(edtYear?.text?.toString()?.toInt()!!)
            } else {
                result = false
                break
            }

        }
        cashModel.cash = arr
        cashModel.period = cashModel.cash?.count()
        cashModel.investment = binding?.edtInvestment?.text.toString().toInt()
        cashModel.interestRate = binding?.edtInterestRate?.text.toString().toFloat()
        cashModel.firstRate = binding?.edtFirstRate?.text.toString().toFloat()
        cashModel.SecondRate = binding?.edtSecondRate?.text.toString().toFloat()

        return result
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    private fun addView() {
        val yearView: View = layoutInflater.inflate(R.layout.row_add_year, null, false)
        val yearText: TextView = yearView.findViewById(R.id.tv_year)
        val buttonCancel = yearView.findViewById(R.id.iv_cancel) as ImageView

        yearText.text = "Year $n"
        n += 1

        buttonCancel.setOnClickListener { removeView(yearView) }

        layoutList?.addView(yearView)
    }

    private fun removeView(view: View) {
        layoutList?.removeView(view)
        n -= 1
    }
}