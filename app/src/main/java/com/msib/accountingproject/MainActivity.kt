package com.msib.accountingproject

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import com.msib.accountingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private var backPressedTime:Long = 0
    lateinit var backToast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // hide action bar
        supportActionBar?.hide()

        binding?.viewFeatureNpv?.setOnClickListener(this)
        binding?.viewFeaturePayback?.setOnClickListener(this)
        binding?.viewFeatureNewIrr?.setOnClickListener(this)
        binding?.viewFeatureAll?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.view_feature_npv -> {
                val intent = Intent(this, NPVActivity::class.java)
                startActivity(intent)
            }
            R.id.view_feature_payback -> {
                val intent = Intent(this, PaybackActivity::class.java)
                startActivity(intent)
            }
            R.id.view_feature_new_irr -> {
                val intent = Intent(this, NewIRRActivity::class.java)
                startActivity(intent)
            }
            R.id.view_feature_all -> {
                val intent = Intent(this, AllActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            finish()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}