package com.example.myapplication

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface SpotifyApiService {
    @GET("popularRadios.json")
    fun getPopularRadios(): Single<List<Radio>>

    @GET("locationRadios.json")
    fun getLocationRadios(): Single<List<Radio>>
}