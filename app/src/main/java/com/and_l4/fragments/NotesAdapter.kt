package com.and_l4.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.and_l4.R
import com.and_l4.models.NoteAndSchedule
import com.and_l4.models.State
import com.and_l4.models.Type
import java.util.*

class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    var items = listOf<NoteAndSchedule>()
        set(value) {
            val diffCallback = NotesDiffCallback(items, value)
            val diffItems = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffItems.dispatchUpdatesTo(this)
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
        return when (viewType) {
            SIMPLE_NOTE -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_note, parent, false)
            )
            else /* SCHEDULED_NOTE */ -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_scheduled_note, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val noteIcon = view.findViewById<ImageView>(R.id.list_item_simpleNote_icon)
        private val noteTitle = view.findViewById<TextView>(R.id.list_item_simpleNote_title)
        private val noteTxt = view.findViewById<TextView>(R.id.list_item_simpleNote_txt)
        private val scheduledNoteIcon = view.findViewById<ImageView>(R.id.list_item_scheduledNote_icon)
        private val scheduledNoteTitle = view.findViewById<TextView>(R.id.list_item_scheduledNote_title)
        private val scheduledNoteTxt = view.findViewById<TextView>(R.id.list_item_scheduledNote_txt)
        private val scheduledNoteState = view.findViewById<TextView>(R.id.list_item_scheduledNote_state)
        private val scheduledNoteClock = view.findViewById<ImageView>(R.id.list_item_scheduledNote_clock)

        fun bind(note: NoteAndSchedule) {
            val iconColor = if (note.note.state == State.DONE) Color.GREEN else Color.GRAY
            if (note.schedule == null) {
                noteTitle.text = note.note.title
                noteTxt.text = note.note.text
                noteIcon.setImageResource(getNoteIcon(note.note.type))
                noteIcon.setColorFilter(iconColor)
            } else {
                scheduledNoteTitle.text = note.note.title
                scheduledNoteTxt.text = note.note.text
                scheduledNoteIcon.setImageResource(getNoteIcon(note.note.type))
                scheduledNoteIcon.setColorFilter(iconColor)
                setNoteState(note, scheduledNoteState, scheduledNoteClock)
            }
        }

        // TODO extract methods to Utils ?
        private fun setNoteState(note: NoteAndSchedule, stateTV: TextView, clock: ImageView) {
            val diffInMillis = (note.schedule!!.date.time.time - Calendar.getInstance().timeInMillis)
            val days = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(diffInMillis)
            var state = "$days days" // TODO extract string ?
            var color = Color.GRAY
            if (days < 0) {
                state = "Late" // TODO extract string ?
                color = Color.RED
            }
            stateTV.text = state
            clock.setColorFilter(color)
        }

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