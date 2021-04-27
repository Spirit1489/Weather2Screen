package ru.spiritblog.weather2screens.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.spiritblog.weather2screens.database.WeatherRepository
import ru.spiritblog.weather2screens.models.Weather
import java.util.*

class WeatherDetailViewModel() : ViewModel() {

    private val weatherRepository = WeatherRepository.get()
    private val weatherIdLiveData = MutableLiveData<UUID>()


    var weatherLiveData: LiveData<Weather?> =
        Transformations.switchMap(weatherIdLiveData) { weatherId ->
            weatherRepository.getWeather(weatherId)

        }


    fun loadWeather(weatherId: UUID) {
        weatherIdLiveData.value = weatherId
    }


    fun saveWeather(weather: Weather) {
        weatherRepository.updateWeather(weather)
    }


}