package com.and_l4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.and_l4.R
import com.and_l4.adapters.RecycleViewAdapter
import com.and_l4.databinding.FragmentNotesBinding
import com.and_l4.models.Note
import com.and_l4.models.State
import com.and_l4.models.Type
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter : RecycleViewAdapter
    private lateinit var binding: FragmentNotesBinding

//    private val notesViewModel: NotesViewModel by activityViewModels {
////        NotesViewModelFactory(/* dataRepo*/)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        binding = FragmentNotesBinding.inflate(inflater, container, false)


        adapter = RecycleViewAdapter()
        val linearLayoutManager = LinearLayoutManager(view.context)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = adapter

        return view
    }

    fun initRecyclerView(){
        val notes = arrayOf(
            Note(1, State.DONE,"test","texts", Calendar.getInstance(), Type.FAMILY),
            Note(1, State.DONE,"test","texts", Calendar.getInstance(), Type.FAMILY),
            Note(1, State.DONE,"test","texts", Calendar.getInstance(), Type.FAMILY)
        )
        adapter = RecycleViewAdapter()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}