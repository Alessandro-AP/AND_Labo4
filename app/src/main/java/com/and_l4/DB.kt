package com.and_l4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.and_l4.models.Note
import com.and_l4.models.NoteDAO
import com.and_l4.models.Schedule
import com.and_l4.models.converters.CalendarConverter

@Database(
    entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(CalendarConverter::class)
abstract class DB : RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object {
        private var INSTANCE: DB? = null

        fun getDataBase(context: Context): DB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "lab4_DB.db"
                )
//                    .addMigrations()
//                    .fallbackToDestructiveMigration()
//                    .addCallback()
                    .build()
                INSTANCE!!
            }
        }
    }
}