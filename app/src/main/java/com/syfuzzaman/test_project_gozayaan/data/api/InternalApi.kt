package com.syfuzzaman.test_project_gozayaan.data.api

import retrofit2.http.GET
import retrofit2.http.Url

interface InternalApi {
    @GET
    suspend fun hotelListApi(
        @Url url: String
    ): List<HotelInfo>
}