package com.and_l4.viewModels

import androidx.lifecycle.ViewModel
import com.and_l4.repositories.DataRepository

class NotesViewModel(private val repository: DataRepository) : ViewModel() {

    val allNotes = repository.allNotes //: LiveData<List<NoteAndSchedule>>
    val countNotes = repository.countNotes //: LiveData<Int>

    fun generateANote() {
        /* création d’une Note aléatoire et insertion dans base de données */
    }

    fun deleteAllNote() {
        /* suppression de toutes les Notes de la base de données */
    }
}