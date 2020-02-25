package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface SpotifyApiService {
    @GET("popularRadios.json")
    fun getPopularRadios(): Call<List<Radio>>

    @GET("locationRadios.json")
    fun getLocationRadios(): Call<List<Radio>>
}