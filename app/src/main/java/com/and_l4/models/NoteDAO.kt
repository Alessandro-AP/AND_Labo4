package com.and_l4.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface NoteDAO {

    @Transaction
    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<NoteAndSchedule>>

    @Query("SELECT COUNT(*) FROM note")
    fun getCount(): LiveData<Int>
}
