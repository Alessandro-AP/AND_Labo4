package com.and_l4

import android.app.Application
import com.and_l4.repositories.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val repository by lazy {
        val database = DB.getDataBase(this)
        DataRepository(database.noteDAO(), applicationScope)
    }
}