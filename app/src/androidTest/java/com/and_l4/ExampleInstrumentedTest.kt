package com.and_l4

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.and_l4.models.Note
import com.and_l4.models.NoteDAO
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

    @Before
    fun createDB() {
        val context : Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, DB::class.java)
            .allowMainThreadQueries()
            .build()
        noteDao = db.noteDAO()
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
        val nId = noteDao.insert(note)
        if (sched != null) {
            sched.ownerId = nId
            noteDao.insert(sched)
        }
        println(noteDao.getCount().value)
        val noteOne = noteDao.getAllNotes().value?.first()
        println(noteOne)
        assertEquals(1, noteDao.getCount().value)
        assertEquals(note.title, noteOne?.note?.title)
    }
}