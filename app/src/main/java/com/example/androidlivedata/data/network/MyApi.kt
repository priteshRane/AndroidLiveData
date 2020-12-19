package com.example.androidlivedata.data.network

import com.example.androidlivedata.data.network.responses.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("moviePagination")
    suspend fun movieResponse(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<MovieResponse>
}