package com.maxticorn.currencies.domain

import io.reactivex.Single

interface RemoteRepository {
    fun getAllCurrencies(): Single<Currencies>
}