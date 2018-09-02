package com.maxticorn.currencies.data.remote

import com.maxticorn.currencies.domain.RemoteRepository

class RemoteRepositoryImpl(private val currenciesAPI: CurrenciesAPI) : RemoteRepository {
    override fun getAllCurrencies() = currenciesAPI.getAllCurrencies()
}