package ru.spiritblog.weather2screens.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.spiritblog.weather2screens.Weather

private const val TAG = "WeatherFetchr"


class WeatherFetchr {

    private val weatherApi: WeatherApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.weather.yandex.ru/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherApi = retrofit.create(WeatherApi::class.java)

    }


    fun fetchWeathers(): LiveData<List<Weather>> {

        val responseLiveData: MutableLiveData<List<Weather>> = MutableLiveData()
        val weatherRequest: Call<ForecastsResponse> = weatherApi.fetchWeathers()


        weatherRequest.enqueue(object : Callback<ForecastsResponse> {

            override fun onFailure(call: Call<ForecastsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch weathers", t)
            }


            override fun onResponse(
                call: Call<ForecastsResponse>,
                response: Response<ForecastsResponse>
            ) {
                Log.d(TAG, "Response received")
                val forecastsResponse: ForecastsResponse? = response.body()
                var weatherForecasts: List<Weather> = forecastsResponse?.weathers?: mutableListOf()
                weatherForecasts = weatherForecasts.filterNot { it.date.isBlank() }
                responseLiveData.value = weatherForecasts
            }

        })

        return responseLiveData

    }


}