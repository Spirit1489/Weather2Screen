package ru.spiritblog.weather2screens.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.spiritblog.weather2screens.Weather


@Database(entities = [Weather::class], version = 1)
@TypeConverters(WeatherTypeConverters::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao


}