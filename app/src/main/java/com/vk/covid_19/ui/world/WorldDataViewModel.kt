package com.vk.covid_19.ui.world

import androidx.lifecycle.*
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.WorldData
import kotlinx.coroutines.launch

class WorldDataViewModel : ViewModel(){
    private val _refreshLiveData = MutableLiveData<Boolean>()
    val refreshLiveData: LiveData<Boolean> = _refreshLiveData

    private val _worldLiveData = MutableLiveData<WorldData>()
    val worldLiveData: LiveData<WorldData> = _worldLiveData

    fun refreshData(){
        viewModelScope.launch {
            _refreshLiveData.value = true
            val data = Covid19Api.retrofitService.getGlobalData()
            _worldLiveData.value = data
            _refreshLiveData.value = false
        }
    }
}