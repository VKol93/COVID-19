package com.vk.covid_19.datasource

import com.vk.covid_19.model.GlobalData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://corona.lmao.ninja"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiServiceTotalScores {
    @GET("/v3/covid-19/all")
    suspend fun getGlobalData(): GlobalData
    @GET("/v3/countries")
    suspend fun fetchAllCountries(): List<CountryD>
}
object Covid19Api {
    val retrofitService : ApiServiceTotalScores by lazy {
        retrofit.create(ApiServiceTotalScores::class.java) }
}