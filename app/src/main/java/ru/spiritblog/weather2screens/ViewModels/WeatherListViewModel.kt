package ru.spiritblog.weather2screens.ViewModels

import androidx.lifecycle.ViewModel
import ru.spiritblog.weather2screens.database.WeatherRepository
import ru.spiritblog.weather2screens.models.Weather

class WeatherListViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository.get()
    val weatherListLiveData = weatherRepository.getWeathers()


    fun addWeather(weather: Weather) {
        weatherRepository.addWeather(weather)
    }



}