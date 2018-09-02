package com.maxticorn.currencies.di

import com.maxticorn.currencies.data.remote.CurrenciesAPI
import com.maxticorn.currencies.data.remote.RemoteRepositoryImpl
import com.maxticorn.currencies.domain.LoadCurrenciesUseCase
import com.maxticorn.currencies.domain.RemoteRepository
import dagger.Module
import javax.inject.Singleton

import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideCurrenciesApi(): CurrenciesAPI {
        val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
                .baseUrl(CurrenciesAPI.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CurrenciesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(currenciesAPI: CurrenciesAPI): RemoteRepository = RemoteRepositoryImpl(currenciesAPI)

    @Provides
    @Singleton
    fun provideLoadCurrenciesUseCase(remoteRepository: RemoteRepository): LoadCurrenciesUseCase = LoadCurrenciesUseCase(remoteRepository)
}
