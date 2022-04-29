package com.and_l4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.and_l4.fragments.NotesFragment
import com.and_l4.viewModels.NotesViewModel
import com.and_l4.viewModels.NotesViewModelFactory

class MainActivity : AppCompatActivity() {

    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as MyApp).repository)
    }
    private lateinit var notesFragment: NotesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.backStackEntryCount == 0) {
            notesFragment = NotesFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.notesFrag, notesFragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.main_menu_generate -> { notesViewModel.generateANote(); true }
            R.id.main_menu_delete -> { notesViewModel.deleteAllNote(); true }
            R.id.main_menu_sort_creationDate -> { notesFragment.adapter.sortItemsByDate(); true }
            R.id.main_menu_sort_eta -> { notesFragment.adapter.sortItemsByETA(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}