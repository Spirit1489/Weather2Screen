package ru.spiritblog.weather2screens.utils

import android.app.Application
import ru.spiritblog.weather2screens.database.WeatherRepository

class Weather2ScreensApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        WeatherRepository.initialize(this)
    }
}