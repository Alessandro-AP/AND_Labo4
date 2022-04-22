package com.and_l4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.and_l4.models.Note
import com.and_l4.models.NoteAndSchedule
import com.and_l4.models.NoteDAO
import com.and_l4.models.Schedule
import com.and_l4.models.converters.CalendarConverter
import com.and_l4.models.converters.StateConverter
import com.and_l4.models.converters.TypeEnumConverter
import kotlin.concurrent.thread

@Database(
    entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(CalendarConverter::class, TypeEnumConverter::class, StateConverter::class)
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
//                    .addCallback(DBCallBack())
                    .build()
                INSTANCE!!
            }
        }

    }

//    private class DBCallBack : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                val isEmpty = database.noteDAO().getCount().value == 0
//                if (isEmpty) {
//                    thread {
//                    // when the database is created for the 1st time, we can, for example, populate it // should be done in a thread
//                    }
//                }
//            }
//        }
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//        }
//
//        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
//            super.onDestructiveMigration(db)
//        }
//    }

}

