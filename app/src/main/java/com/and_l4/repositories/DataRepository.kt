// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.repositories

import com.and_l4.room.models.Note
import com.and_l4.room.NoteDAO
import com.and_l4.room.models.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Repository managing notes.
 */
class DataRepository(private val noteDAO: NoteDAO, private val scope : CoroutineScope) {

    val allNotes = noteDAO.getAllNotes() //: LiveData<List<NoteAndSchedule>>
    val countNotes = noteDAO.getCount() //: LiveData<Int>

    fun insertNote(note: Note, schedule: Schedule?) {
        scope.launch(Dispatchers.IO) {
            val nId = noteDAO.insert(note)
            if (schedule != null) {
                schedule.ownerId = nId
                noteDAO.insert(schedule)
            }
        }
    }

    fun deleteAll() {
        scope.launch(Dispatchers.IO) {
            noteDAO.deleteAllSchedules()
            noteDAO.deleteAllNotes()
        }
    }
}
