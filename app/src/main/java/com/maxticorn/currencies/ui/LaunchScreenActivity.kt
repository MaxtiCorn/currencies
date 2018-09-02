package com.maxticorn.currencies.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LaunchScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, CurrenciesActivity::class.java)
        startActivity(intent)
        finish()
    }
}