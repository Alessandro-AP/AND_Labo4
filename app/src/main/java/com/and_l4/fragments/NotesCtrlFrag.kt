// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.and_l4.MyApp
import com.and_l4.R
import com.and_l4.viewModels.NotesViewModel
import com.and_l4.viewModels.NotesViewModelFactory

/**
 * A fragment showing the number of notes created and two buttons to manage the notes.
 */
class NotesCtrlFrag : Fragment(R.layout.fragment_notes_ctrl) {

    private val notesViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val counterTV = view.findViewById<TextView>(R.id.ctrl_frag_counterTV)
        val generateBtn = view.findViewById<Button>(R.id.ctrl_frag_generate_btn)
        val deleteBtn = view.findViewById<Button>(R.id.ctrl_frag_delete_btn)

        notesViewModel.countNotes.observe(viewLifecycleOwner) { value ->
            counterTV.text = value.toString()
        }

        generateBtn.setOnClickListener {
            notesViewModel.generateANote()
        }

        deleteBtn.setOnClickListener {
            notesViewModel.deleteAllNote()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesCtrlFrag()
    }
}