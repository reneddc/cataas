package com.calyr.network

class CatsRemoteDataSource(
    private val retrofitService: RetrofitBuilder
) {
    suspend fun getCatsInfo(): List<ResponseDto> {
        return retrofitService.apiService.getCatsInfo()
    }
}
