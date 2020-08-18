package com.facil.notes.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facil.notes.R
import com.facil.notes.pojos.Note
import com.facil.notes.ui.fragments.note_list.NoteListContract

/**
 * [RecyclerView.Adapter] that can display a [Note].
 */
class MyNoteRecyclerViewAdapter(
    private val mNotes: ArrayList<Note>,
    private val onNoteSelectedListener: NoteListContract.OnNoteSelectedListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>() {

    val loading = true;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mNotes[position]
        holder.bind(item, onNoteSelectedListener)
    }

    override fun getItemCount(): Int = mNotes.size

    fun setItems(notes: ArrayList<Note>) {
        mNotes.clear()
        mNotes.addAll(notes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        private val llTagContainer: LinearLayout = view.findViewById(R.id.llTagContainer)
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvNoteListItemContent: TextView = view.findViewById(R.id.tvNoteListItemContent)

        fun bind(item: Note, onNoteSelectedListener: NoteListContract.OnNoteSelectedListener) {
            tvTitle.text = item.id
            tvNoteListItemContent.text = item.title
            if (item.tag.isEmpty()) {
                llTagContainer.visibility = View.GONE
            } else {
                val tvTagTitle = llTagContainer.findViewById<TextView>(R.id.tvTag)
                val tvTagIcon = llTagContainer.findViewById<TextView>(R.id.tvIcon)

                tvTagTitle.text = item.tag
                tvTagIcon.setBackgroundColor(Color.parseColor(item.tagColour))
            }

            itemView.setOnClickListener{ onNoteSelectedListener.onNoteSelected(item) }
        }

        override fun toString(): String {
            return super.toString() + " '" + tvNoteListItemContent.text + "'"
        }
    }
}