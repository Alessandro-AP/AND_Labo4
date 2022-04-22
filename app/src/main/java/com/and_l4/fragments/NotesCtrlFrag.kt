package com.and_l4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.and_l4.MyApp
import com.and_l4.R
import com.and_l4.viewModels.NotesViewModel
import com.and_l4.viewModels.NotesViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotesCtrlFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesCtrlFrag : Fragment(R.layout.fragment_notes_ctrl) {

    private val notesViewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory((requireActivity().application as MyApp).repository)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    // TODO question : pourquoi ne pas passer le layout dans le constructeur de la classe ?? (Moins de code)
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_notes_ctrl, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val counterTV = view.findViewById<TextView>(R.id.ctrl_frag_counterTV)
        val generateBtn = view.findViewById<Button>(R.id.ctrl_frag_generate_btn)
        val deleteBtn = view.findViewById<Button>(R.id.ctrl_frag_delete_btn)

        counterTV.text = notesViewModel.countNotes.toString()

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