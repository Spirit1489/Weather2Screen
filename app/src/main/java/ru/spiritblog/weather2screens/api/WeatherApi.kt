package ru.spiritblog.weather2screens.api

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.Headers

interface WeatherApi {

    @GET("/")

    fun fetchContents(): Call<String>


    @GET(
        "forecast?lat=55.75396" +
                "&lon=37.620393" +
                "&limit=7" +
                "&hours=false"+
                "&extra=false"

    )

    @Headers(
        "X-Yandex-API-Key:0acc1fc6-c409-4208-bfd7-bd1f232c1775"
    )


    fun fetchWeathers(): Call<ForecastsResponse>


}