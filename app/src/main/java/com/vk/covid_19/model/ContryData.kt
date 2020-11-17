package com.vk.covid_19.model


data class Country(
    var info: CountryInfo,
    var cases: Int,
    var name: String,
    var deaths: Int,
    var population: Int,
    var recovered: Int,
    var tests: Int,
    var todayCases: Int,
    var todayDeaths: Int,
    var todayRecovered: Int
)

data class CountryInfo(
    var flag: String,
)