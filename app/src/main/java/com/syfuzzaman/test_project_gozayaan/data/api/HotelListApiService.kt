package com.syfuzzaman.test_project_gozayaan.data.api

import javax.inject.Inject

class HotelListApiService @Inject constructor(
    private val api: InternalApi
){
    suspend fun execute(): List<HotelInfo> {
        return api.hotelListApi("https://d9c8de84d7e4424dbbb59e258f353159.api.mockbin.io/")
    }
}