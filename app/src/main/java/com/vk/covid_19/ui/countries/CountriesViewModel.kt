package com.vk.covid_19.ui.countries

import androidx.lifecycle.*
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.CountryData
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel(){
    enum class SortedType {
        BY_NAME, BY_CASES
    }
    private val _countriesLiveData = MutableLiveData<List<CountryData>>()
    val countriesLiveData: LiveData<List<CountryData>> = _countriesLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun refreshData(){
        viewModelScope.launch {
            _loadingLiveData.value = true
            val countries: List<CountryData> = Covid19Api.retrofitService.fetchAllCountries()
            _countriesLiveData.value = countries
            _loadingLiveData.value = false
        }
    }

    fun changeSortedType(sortedType: SortedType){
        val countries = countriesLiveData.value
        val sortedCountries = when(sortedType){
            SortedType.BY_NAME -> countries?.sortedBy{it.name}
            SortedType.BY_CASES ->countries?.sortedBy{it.cases}
        }
        _countriesLiveData.value = sortedCountries
    }
}