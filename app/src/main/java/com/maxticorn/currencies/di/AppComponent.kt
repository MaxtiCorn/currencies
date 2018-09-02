package com.maxticorn.currencies.di

import com.maxticorn.currencies.ui.CurrenciesListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(currenciesListViewModel: CurrenciesListViewModel)
}