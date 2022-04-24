package com.and_l4.models.converters

import androidx.room.TypeConverter
import com.and_l4.models.State

class StateConverter {
    @TypeConverter
    fun fromStateEnum(state: State) = state.name

    @TypeConverter
    fun toStateEnum(name: String) = State.valueOf(name)
}