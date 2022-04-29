// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.and_l4.fragments.NotesFragment
import androidx.core.content.edit
import com.and_l4.viewModels.NotesViewModel
import com.and_l4.viewModels.NotesViewModelFactory

/**
 * Application entry point.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as MyApp).repository)
    }
    private lateinit var notesFragment: NotesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesFragment = supportFragmentManager.findFragmentById(R.id.notesFrag) as NotesFragment
        prefs = getSharedPreferences(PREFERENCE_FILE_NAME, MODE_PRIVATE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.main_menu_generate -> { notesViewModel.generateANote(); true }
            R.id.main_menu_delete -> { notesViewModel.deleteAllNote(); true }
            R.id.main_menu_sort_creationDate -> {
                prefs.edit { putString(USER_CHOICE, CREATION_DATE) }
                notesFragment.adapter.sortItemsByDate()
                true
            }
            R.id.main_menu_sort_eta -> {
                prefs.edit { putString(USER_CHOICE, ETA) }
                notesFragment.adapter.sortItemsByETA()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val PREFERENCE_FILE_NAME = "userSortChoice"
        const val USER_CHOICE = "USER_CHOICE"
        const val ETA = "ETA"
        const val CREATION_DATE = "CREATION_DATE"
    }
}