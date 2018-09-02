package com.maxticorn.currencies.domain

import io.reactivex.Single

class LoadCurrenciesUseCase(private val remoteRepository: RemoteRepository) {
    fun loadAllCurrencies(): Single<Currencies> = remoteRepository.getAllCurrencies()
}