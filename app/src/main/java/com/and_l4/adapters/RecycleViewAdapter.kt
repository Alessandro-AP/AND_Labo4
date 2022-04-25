package com.and_l4.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.and_l4.R
import com.and_l4.models.Note
import com.and_l4.models.State
import com.and_l4.models.Type
import java.util.*


class RecycleViewAdapter :
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    private val notes = arrayOf(
        Note(1, State.DONE,"test","texts", Calendar.getInstance(), Type.FAMILY),
        Note(1, State.DONE,"test","texts", Calendar.getInstance(), Type.FAMILY),
        Note(1, State.DONE,"test","texts", Calendar.getInstance(), Type.FAMILY)
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image    : ImageView
        var title    : TextView
        var subtitle : TextView

        init {
            image     = itemView.findViewById(R.id.list_item_icon)
            title     = itemView.findViewById(R.id.list_item_title)
            subtitle  = itemView.findViewById(R.id.list_item_subtitle)

            itemView.setOnClickListener {
                val position = adapterPosition

                Toast.makeText(itemView.context, "You clicked on ${notes[position].title}", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Bind datas with layout
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = notes[position].title
        viewHolder.subtitle.text = notes[position].text

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = notes.size

}