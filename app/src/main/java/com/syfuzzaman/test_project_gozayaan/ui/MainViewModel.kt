package com.syfuzzaman.test_project_gozayaan.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.data.api.HotelListApiService
import com.syfuzzaman.test_project_gozayaan.data.model.Resource
import com.syfuzzaman.test_project_gozayaan.data.utils.resultFromResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hotelListApiService: HotelListApiService
) : ViewModel() {

    val hotelList = MutableLiveData<Resource<List<HotelInfo>?>>()

    fun callHotelListApi(){
        viewModelScope.launch{
            val response = resultFromResponse { hotelListApiService.execute() }
            hotelList.value = response
        }
    }
}