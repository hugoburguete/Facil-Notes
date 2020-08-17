package com.facil.notes.ui.fragments.note_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facil.notes.R
import com.facil.notes.ui.adapters.MyNoteRecyclerViewAdapter
import com.facil.notes.framework.BaseFragment
import com.facil.notes.pojos.Note
import com.facil.notes.repositories.NotesRepository

/**
 * A fragment representing a list of Items.
 */
class NotesListFragment : BaseFragment(), NoteListContract.View {
    private val presenter: NoteListContract.Presenter = NoteListPresenter(this, NotesRepository())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        presenter.loadNotes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)
        val lvNotes = view.findViewById<RecyclerView>(R.id.lvNotes)
        lvNotes.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onNoteSelected(note: Note) {
        (activity as NoteListContract.OnNoteSelectedListener).onNoteSelected(note)
    }

    override fun onNotesLoaded(notes: ArrayList<Note>) {
        val lvNotes = view?.findViewById<RecyclerView>(R.id.lvNotes)
        if (lvNotes != null) {
            lvNotes.adapter = MyNoteRecyclerViewAdapter(notes, this)
        }
    }
}