package com.and_l4

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.and_l4.models.Note
import com.and_l4.models.NoteDAO
import com.and_l4.repositories.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

    private lateinit var noteDao: NoteDAO
    private lateinit var db : DB
    private lateinit var repository: DataRepository

    @Before
    fun createDB() {
        val context : Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, DB::class.java)
            .allowMainThreadQueries()
            .build()
//        db = DB.getDataBase(context)
        noteDao = db.noteDAO()
        repository = DataRepository(noteDao, CoroutineScope(SupervisorJob()))
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetNote() : Unit = runBlocking {
        val note = Note.generateRandomNote()
        val sched = Note.generateRandomSchedule()
        repository.insertNote(note, sched)
        val notes = noteDao.getAllNotesNoLiveData()
        val notesLiveData = repository.allNotes.value
        assertEquals(note.title, notes.first().note.title)
//        assertEquals(1, repository.countNotes.value)
        assertEquals(note.title, notesLiveData?.first()!!.note.title)
    }
}