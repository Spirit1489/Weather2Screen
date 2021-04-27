package ru.spiritblog.weather2screens.database

import androidx.room.TypeConverter
import java.util.*

class WeatherTypeConverters {


    @TypeConverter
    fun fromInt(int: Int?): String? {
        return int.toString()

    }

    @TypeConverter
    fun toInt(int: String?): Int? {
        return int?.toInt()
    }


    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }


    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {

        return uuid?.toString()

    }



































}