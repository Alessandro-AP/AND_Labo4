// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4

import android.app.Application
import com.and_l4.repositories.DataRepository
import com.and_l4.room.NotesDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val repository by lazy {
        val database = NotesDB.getDataBase(this)
        DataRepository(database.noteDAO(), applicationScope)
    }
}