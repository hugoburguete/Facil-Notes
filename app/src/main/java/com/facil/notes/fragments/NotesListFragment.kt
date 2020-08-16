package com.facil.notes.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facil.notes.R
import com.facil.notes.activities.main.MainActivity
import com.facil.notes.presenters.MainActivityContract
import com.facil.notes.adapters.MyNoteRecyclerViewAdapter
import com.facil.notes.framework.BaseFragment
import com.facil.notes.pojos.Note

/**
 * A fragment representing a list of Items.
 */
class NotesListFragment : BaseFragment(), MainActivityContract.OnNoteSelectedListener {

    var presenter: MainActivityContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list_list, container, false)
        val lvNotes = view.findViewById<RecyclerView>(R.id.lv_notes)
        lvNotes.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onNoteSelected(note: Note) {
        (activity as MainActivity).onNoteSelected(note)
    }

    fun onNotesLoaded(notes: ArrayList<Note>) {
        val lvNotes = view?.findViewById<RecyclerView>(R.id.lv_notes)
        if (lvNotes != null) {
            lvNotes.adapter = MyNoteRecyclerViewAdapter(notes, this)
        }
    }
}