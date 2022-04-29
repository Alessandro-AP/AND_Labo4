// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.and_l4.room.models.Note
import com.and_l4.room.models.Schedule
import com.and_l4.room.models.converters.CalendarConverter
import com.and_l4.room.models.converters.StateConverter
import com.and_l4.room.models.converters.TypeEnumConverter

@Database(
    entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(CalendarConverter::class, TypeEnumConverter::class, StateConverter::class)
abstract class NotesDB : RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object {
        private var INSTANCE: NotesDB? = null

        fun getDataBase(context: Context): NotesDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "lab4_DB"
                )
                    .build()
                INSTANCE!!
            }
        }
    }
}

