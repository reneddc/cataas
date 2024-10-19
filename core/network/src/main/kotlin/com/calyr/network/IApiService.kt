package com.calyr.network

import retrofit2.http.GET

interface IApiService {

    @GET("/api/cats?limit=10&skip=0")
    suspend fun getCatsInfo(): List<ResponseDto>
}
