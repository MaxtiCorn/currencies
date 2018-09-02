package com.maxticorn.currencies.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.maxticorn.currencies.App
import com.maxticorn.currencies.domain.Currencies
import com.maxticorn.currencies.domain.Currency
import com.maxticorn.currencies.domain.LoadCurrenciesUseCase
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesListViewModel(application: Application) : AndroidViewModel(application) {
    val currenciesLiveData = MutableLiveData<List<Currency>>()
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()

    companion object {
        const val TAG = "LIST_VIEW_MODEL"
    }

    @Inject
    lateinit var loadCurrenciesUseCase: LoadCurrenciesUseCase

    init {
        (application as App).appComponent.inject(this)
        currenciesLiveData.postValue(null)
        showLoading.postValue(true)
        loadCurrenciesUseCase.loadAllCurrencies()
                .repeatWhen { observable -> observable.delay(15, TimeUnit.SECONDS) }
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe({ currencies ->
                    onLoadCurrencies(currencies)
                    Log.d(TAG, "Currencies successfully loaded")
                }, {
                    showLoading.postValue(false)
                    showError.postValue(true)
                    Log.d(TAG, "Error while currencies loading")
                })
    }

    fun updateCurrencies() {
        showLoading.postValue(true)
        loadCurrenciesUseCase.loadAllCurrencies()
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe({ currencies ->
                    onLoadCurrencies(currencies)
                    Log.d(TAG, "Currencies successfully loaded")
                }, {
                    showLoading.postValue(false)
                    showError.postValue(true)
                    Log.d(TAG, "Error while currencies loading")
                })
    }

    private fun onLoadCurrencies(currencies: Currencies) {
        currenciesLiveData.postValue(currencies.stock)
        showLoading.postValue(false)
    }
}