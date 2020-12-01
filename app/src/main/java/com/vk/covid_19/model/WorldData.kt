package com.vk.covid_19.model

data class WorldData(
    var active: Int,
    var activePerOneMillion: Double,
    var affectedCountries: Int,
    var cases: Int,
    var casesPerOneMillion: Double,
    var critical: Int,
    var criticalPerOneMillion: Double,
    var deaths: Int,
    var deathsPerOneMillion: Double,
    var oneCasePerPeople: Double,
    var oneDeathPerPeople: Double,
    var oneTestPerPeople: Double,
    var population: Long,
    var recovered: Int,
    var recoveredPerOneMillion: Double,
    var tests: Int,
    var testsPerOneMillion: Double,
    var todayCases: Int,
    var todayDeaths: Int,
    var todayRecovered: Int,
    var updated: Long
)