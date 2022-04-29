// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.viewModels

import androidx.lifecycle.ViewModel
import com.and_l4.room.models.Note
import com.and_l4.repositories.DataRepository

/**
 * ViewModel managing interaction with the data through the repository.
 */
class NotesViewModel(private val repository: DataRepository) : ViewModel() {

    val allNotes = repository.allNotes //: LiveData<List<NoteAndSchedule>>
    val countNotes = repository.countNotes //: LiveData<Int>

    /**
     * Generate a random note and insert it into the database.
     */
    fun generateANote() {
        repository.insertNote(Note.generateRandomNote(), Note.generateRandomSchedule())
    }

    /**
     * Delete all notes from the database.
     */
    fun deleteAllNote() {
        repository.deleteAll()
    }
}