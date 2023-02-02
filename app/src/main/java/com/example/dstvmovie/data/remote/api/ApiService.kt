package com.example.dstvmovie.data.remote.api

import com.example.dstvmovie.data.remote.response.BaseListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/top5MoviesAssessement.json")
    suspend fun getDiscoverMovie(): BaseListResponse
}