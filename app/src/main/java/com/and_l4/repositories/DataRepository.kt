package com.and_l4.repositories

import kotlinx.coroutines.CoroutineScope

class DataRepository(private val noteDAO: NoteDAO, private val scope : CoroutineScope) {

    val allNotes = noteDAO.getAllNotes() //: LiveData<List<NoteAndSchedule>>
    val countNotes = noteDAO.getCount() //: LiveData<Int>

}
