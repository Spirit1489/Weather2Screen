package ru.spiritblog.weather2screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.spiritblog.weather2screens.database.WeatherRepository

class WeatherListViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository.get()
    val weatherListLiveData = weatherRepository.getWeathers()


    fun addWeather(weather: Weather) {
        weatherRepository.addWeather(weather)
    }






}