// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.and_l4.MyApp
import com.and_l4.R
import com.and_l4.viewModels.NotesViewModel
import com.and_l4.viewModels.NotesViewModelFactory
import com.and_l4.MainActivity.Companion.PREFERENCE_FILE_NAME
import com.and_l4.MainActivity.Companion.USER_CHOICE
import com.and_l4.MainActivity.Companion.ETA

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val notesViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory((requireActivity().application as MyApp).repository)
    }

    lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.frag_notes_recyclerView)
        adapter = NotesAdapter(context)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        notesViewModel.allNotes.observe(viewLifecycleOwner) { value ->
            adapter.items = value

            val sorting = requireActivity().getSharedPreferences(
                PREFERENCE_FILE_NAME,
                AppCompatActivity.MODE_PRIVATE
            ).getString(USER_CHOICE, null)
            // by default the list is ordered by creation date (notes ids are
            // ordered in the same way), thus checking for this is useless in
            // the lab context (may be different for another project)
            if (sorting == ETA)
                adapter.sortItemsByETA()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}