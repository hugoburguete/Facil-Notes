package com.facil.notes.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facil.notes.R
import com.facil.notes.ui.fragments.note_list.NoteListContract

import com.facil.notes.pojos.Note

/**
 * [RecyclerView.Adapter] that can display a [Note].
 */
class MyNoteRecyclerViewAdapter(
    private val mNotes: ArrayList<Note>,
    private val onNoteSelectedListener: NoteListContract.OnNoteSelectedListener
) : RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notes_list, parent, false)
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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idView: TextView = view.findViewById(R.id.item_number)
        private val contentView: TextView = view.findViewById(R.id.content)

        fun bind(item: Note, onNoteSelectedListener: NoteListContract.OnNoteSelectedListener) {
            idView.text = item.id
            contentView.text = item.title
            itemView.setOnClickListener{ onNoteSelectedListener.onNoteSelected(item) }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}