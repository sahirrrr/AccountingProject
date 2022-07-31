package com.msib.accountingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class IRRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_irr)

        // hide action bar
        supportActionBar?.hide()
    }
}