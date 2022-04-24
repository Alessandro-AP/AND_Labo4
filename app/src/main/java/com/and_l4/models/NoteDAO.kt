package com.and_l4.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

    @Insert
    fun insert(note: Note): Long

    @Insert
    fun insert(schedule: Schedule): Long

    @Delete
    fun delete(note: Note)
    @Delete
    fun delete(schedule: Schedule)

    @Query("DELETE FROM note")
    fun deleteAllNotes()

    @Query("DELETE FROM schedule")
    fun deleteAllSchedules()

    @Transaction
    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<NoteAndSchedule>>

    @Query("SELECT COUNT(*) FROM note")
    fun getCount(): LiveData<Int>
}
