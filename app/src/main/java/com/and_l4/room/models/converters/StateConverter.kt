// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.room.models.converters

import androidx.room.TypeConverter
import com.and_l4.room.models.State

class StateConverter {
    @TypeConverter
    fun fromStateEnum(state: State) = state.name

    @TypeConverter
    fun toStateEnum(name: String) = State.valueOf(name)
}