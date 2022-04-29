// AND - Labo 4
// Authors : Alessandro Parrino, Daniel Sciarra, Wilfried Karel Ngueukam Djeuda ◕◡◕
// Date: 01.05.22
package com.and_l4.fragments

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.and_l4.R
import com.and_l4.databinding.ListItemNoteBinding
import com.and_l4.room.models.NoteAndSchedule
import com.and_l4.room.models.State
import com.and_l4.room.models.Type
import java.util.Calendar
import java.util.concurrent.TimeUnit

/**
 * RecyclerViewAdapter used to manage a list of notes and deliver it to a RecyclerView.
 */
class NotesAdapter(private val context: Context?) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var items = listOf<NoteAndSchedule>()
        set(value) {
            val diffCallback = NotesDiffCallback(items, value)
            val diffItems = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffItems.dispatchUpdatesTo(this)
        }

    /**
     * Sort the notes list according to the note's creation date.
     */
    fun sortItemsByDate(){
        items = items.sortedBy { it.note.creationDate }
    }

    /**
     * Sort the notes list according to the note's Estimated Time of Arrival.
     */
    fun sortItemsByETA(){
        items = items.sortedBy { it.schedule?.date }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position].schedule == null) SIMPLE_NOTE
        else SCHEDULED_NOTE
    }

    companion object {
        private const val SIMPLE_NOTE = 1
        private const val SCHEDULED_NOTE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /**
     * ViewHolder class containing the logic of the list item settings.
     */
    inner class ViewHolder(private val binding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: NoteAndSchedule) {
            val iconColor = if (note.note.state == State.DONE) Color.GREEN else Color.GRAY
            binding.listItemTitle.text = note.note.title
            binding.listItemTxt.text = note.note.text
            binding.listItemIcon.setImageResource(getNoteIcon(note.note.type))
            binding.listItemIcon.setColorFilter(iconColor)
            if (note.schedule != null) {
                binding.listItemStateGroup.visibility = View.VISIBLE
                setNoteState(note)
            }
        }

        /**
         * Determine the state of the note (due date and icon color) and set it accordingly.
         */
        private fun setNoteState(note: NoteAndSchedule) {
            val diffInMillis =
                note.schedule!!.date.timeInMillis - Calendar.getInstance().timeInMillis
            val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
            var state = context?.getString(R.string.note_due_date, days)
            var color = Color.GRAY
            if (days <= 0) {
                state = context?.getString(R.string.note_outdated)
                color = Color.RED
            }
            binding.listItemState.text = state
            binding.listItemClock.setColorFilter(color)
        }

        /**
         * Retrieve the right note's drawable from its type.
         */
        private fun getNoteIcon(noteType: Type): Int {
            return when (noteType) {
                Type.FAMILY -> R.drawable.family
                Type.SHOPPING -> R.drawable.shopping
                Type.TODO -> R.drawable.todo
                Type.WORK -> R.drawable.work
                else -> R.drawable.note
            }
        }
    }
}

/**
 * Class used to compare the identity between the elements of two lists.
 */
class NotesDiffCallback(private val oldList: List<NoteAndSchedule>, private val newList: List<NoteAndSchedule>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].note.noteId == newList[newItemPosition].note.noteId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old::class == new::class && old.note == new.note
    }
}