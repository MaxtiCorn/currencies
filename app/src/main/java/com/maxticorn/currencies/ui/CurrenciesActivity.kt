package com.maxticorn.currencies.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.maxticorn.currencies.R
import kotlinx.android.synthetic.main.activity_currencies_list.*
import kotlinx.android.synthetic.main.toolbar.*

class CurrenciesActivity : AppCompatActivity() {
    private lateinit var currenciesListViewModel: CurrenciesListViewModel
    private lateinit var currenciesRecyclerViewAdapter: CurrenciesRecyclerAdapter

    companion object {
        const val TAG = "CURRENCIES_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies_list)

        currencies_list.layoutManager = LinearLayoutManager(applicationContext)
        currenciesRecyclerViewAdapter = CurrenciesRecyclerAdapter()
        currencies_list.adapter = currenciesRecyclerViewAdapter

        currenciesListViewModel = ViewModelProviders.of(this)[CurrenciesListViewModel::class.java]

        swipe_refresh.setOnRefreshListener { currenciesListViewModel.updateCurrencies() }

        update_button.setOnClickListener {
            currenciesListViewModel.updateCurrencies()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        currenciesListViewModel.currenciesLiveData.observe(this, Observer { newCurrencies ->
            currenciesRecyclerViewAdapter.currencies = newCurrencies
            currenciesRecyclerViewAdapter.notifyDataSetChanged()
            Log.d(TAG, "Currencies successfully loaded")
        })
        currenciesListViewModel.showLoading.observe(this, Observer {
            swipe_refresh.isRefreshing = it ?: false
        })
        currenciesListViewModel.showError.observe(this, Observer {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
        })
    }
}