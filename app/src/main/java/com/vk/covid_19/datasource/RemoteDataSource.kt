package com.vk.covid_19.datasource

import com.vk.covid_19.model.CountryData
import com.vk.covid_19.model.WorldData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/covid-19/all")
    suspend fun getGlobalData(): WorldData
    @GET("/v2/countries")
    suspend fun fetchAllCountries(): List<CountryData>
}
object Covid19Api {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
    private const val BASE_URL = "https://corona.lmao.ninja"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}
