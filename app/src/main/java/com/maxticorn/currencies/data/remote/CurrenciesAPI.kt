package com.maxticorn.currencies.data.remote

import com.maxticorn.currencies.domain.Currencies
import io.reactivex.Single
import retrofit2.http.GET

interface CurrenciesAPI {
    companion object {
        val BASE_URL: String = "http://phisix-api3.appspot.com"
    }

    @GET("/stocks.json")
    fun getAllCurrencies(): Single<Currencies>
}