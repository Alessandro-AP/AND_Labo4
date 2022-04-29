// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.and_l4.room.models.Note
import com.and_l4.room.models.NoteAndSchedule
import com.and_l4.room.models.Schedule

/**
 * DAO managing notes interaction with the database.
 */
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
