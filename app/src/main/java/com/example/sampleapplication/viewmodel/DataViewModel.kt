package com.example.sampleapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapplication.model.DetailsData
import com.example.sampleapplication.network.Resource

class DataViewModel : ViewModel() {

    var volumesResponseLiveData: MutableLiveData<Resource<List<DetailsData>>>? = null
    private var dataRepository: DataRepository = DataRepository()

    init {
        volumesResponseLiveData = dataRepository.getMutableList()
    }

    fun getDescriptionListData() {
        dataRepository.getDescriptionData()
    }

    fun getBirthdayListData() {
        dataRepository.getBirthdayList()
    }

}