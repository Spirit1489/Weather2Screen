package ru.spiritblog.weather2screens.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*



@Entity

data class Weather(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var cityTitle: String = "",
    var date: String = "",
    var temperature: Int = 0,
    var description: String = "",



)













