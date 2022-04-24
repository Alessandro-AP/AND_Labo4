package com.and_l4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.and_l4.MyApp
import com.and_l4.R
import com.and_l4.models.NoteAndSchedule
import com.and_l4.viewModels.NotesViewModel
import com.and_l4.viewModels.NotesViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val notesViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.frag_notes_recyclerView)
        val adapter = NotesAdapter(context)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        notesViewModel.allNotes.observe(viewLifecycleOwner) { value ->
            adapter.items = value
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}