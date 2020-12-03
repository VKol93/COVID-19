package com.vk.covid_19.ui.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.CountryData
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel(){
    val countriesLiveData = MutableLiveData<List<CountryData>>()

    fun refreshData(){
        viewModelScope.launch {
            val countries: List<CountryData> = Covid19Api.retrofitService.fetchAllCountries()
            countriesLiveData.value = countries
        }
    }
}