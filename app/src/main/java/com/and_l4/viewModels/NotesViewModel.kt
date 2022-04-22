package com.and_l4.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.and_l4.models.Note
import com.and_l4.models.NoteAndSchedule
import com.and_l4.repositories.DataRepository

class NotesViewModel(private val repository: DataRepository) : ViewModel() {

    val allNotes = repository.allNotes //: LiveData<List<NoteAndSchedule>>
    val countNotes = repository.countNotes //: LiveData<Int>

    fun generateANote() {
        /* création d’une Note aléatoire et insertion dans base de données */
        repository.insertNote(Note.generateRandomNote(), Note.generateRandomSchedule())
    }

    fun deleteAllNote() {
        /* suppression de toutes les Notes de la base de données */
        repository.deleteAll()
    }
}