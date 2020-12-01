package com.vk.covid_19.ui.world

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.covid_19.datasource.Covid19Api
import com.vk.covid_19.model.WorldData
import kotlinx.coroutines.launch

class WorldDataViewModel : ViewModel(){
    val worldLiveData = MutableLiveData<WorldData>()

    fun refreshData(){
        viewModelScope.launch {
            val data = Covid19Api.retrofitService.getGlobalData()
            worldLiveData.value = data
        }
    }
}