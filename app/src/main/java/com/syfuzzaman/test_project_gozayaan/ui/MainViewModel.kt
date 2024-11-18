package com.syfuzzaman.test_project_gozayaan.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.data.api.HotelListApiService
import com.syfuzzaman.test_project_gozayaan.data.model.Resource
import com.syfuzzaman.test_project_gozayaan.data.utils.resultFromResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hotelListApiService: HotelListApiService
) : ViewModel() {

    val hotelList = MutableLiveData<Resource<List<HotelInfo>?>>()
    val searchQuery = MutableLiveData<String>()
    val searchResult = MutableLiveData<List<HotelInfo>>()
    val isSearchResultEmpty = MutableSharedFlow<Boolean>()


    fun callHotelListApi(){
        viewModelScope.launch{
            val response = resultFromResponse { hotelListApiService.execute() }
            hotelList.value = response
        }
    }

    fun getHotelList(): List<HotelInfo>? {
        return (hotelList.value as? Resource.Success)?.data
    }

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.Default){
            if (query.isNotEmpty()){
                searchQuery.postValue(query)

                val originalList = (hotelList.value as? Resource.Success)?.data ?: emptyList()
                val result = originalList.filter { hotelInfo ->
                    hotelInfo.propertyName?.contains(query, ignoreCase = true) == true ||
                            hotelInfo.location?.contains(query, ignoreCase = true) == true
                }
                if (result.isNotEmpty()){
                    isSearchResultEmpty.emit(false)
                    searchResult.postValue(result)
                } else {
                    isSearchResultEmpty.emit(true)

                }
            }
        }
    }

    fun clearSearchItems(){
        searchQuery.value = ""
        searchResult.value = emptyList()
    }
}