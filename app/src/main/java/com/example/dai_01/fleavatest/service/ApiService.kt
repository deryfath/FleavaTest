package com.example.dai_01.fleavatest.service

import com.example.dai_01.fleavatest.model.LoginResponse
import com.example.dai_01.fleavatest.model.ResponseData
import com.example.dai_01.fleavatest.model.WeatherListResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/sign-in")
    fun login(@Body body: Map<String, String>): Observable<LoginResponse>

}