package com.facil.notes.ui.fragments.note_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import com.facil.notes.R
import com.facil.notes.framework.BaseActivity
import com.facil.notes.framework.BaseFragment
import com.facil.notes.pojos.NoteWithTags
import com.facil.notes.repositories.NotesRepository
import com.facil.notes.ui.adapters.MyNoteRecyclerViewAdapter

/**
 * A fragment representing a list of Items.
 */
class NotesListFragment : BaseFragment(), NoteListContract.View {
    private var presenter: NoteListContract.Presenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        val appDatabase = (activity as BaseActivity).getDatabase()
        presenter = NoteListPresenter(this, NotesRepository(appDatabase))
        presenter?.loadNotes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)

        // ListView
        val lvNotes = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.lvNotes)
        lvNotes.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        lvNotes.adapter = MyNoteRecyclerViewAdapter(ArrayList(), this)

        // Search Text
        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                if (str.isEmpty()) {
                    presenter?.loadNotes()
                } else {
                    presenter?.searchNotes(str)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        return view
    }

    override fun onNoteSelected(noteWithTags: NoteWithTags) {
        (activity as NoteListContract.OnNoteSelectedListener).onNoteSelected(noteWithTags)
    }

    override fun onNotesLoading() {
        // Display Progress Bar
        val progressBar = view?.findViewById<ProgressBar>(R.id.pgNotesList)
        progressBar?.visibility = View.VISIBLE

        // Hide ListView
        val lvNotes = view?.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.lvNotes)
        lvNotes?.visibility = View.GONE
    }

    override fun onNotesLoaded(notes: List<NoteWithTags>) {
        updateListView(notes)
    }

    override fun onNotesLoadFailure(e: Exception) {
        println("failed to load notes")
        println(e)
    }

    override fun onNotesFound(notes: List<NoteWithTags>) {
        updateListView(notes)
    }

    override fun onNotesSearchFailure(e: Exception) {
        TODO("Not yet implemented")
    }

    private fun updateListView(notes: List<NoteWithTags>) {
        // Hide Progress Bar
        val progressBar = view?.findViewById<ProgressBar>(R.id.pgNotesList)
        progressBar?.visibility = View.GONE

        // Display ListView
        val lvNotes = view?.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.lvNotes)
        lvNotes?.visibility = View.VISIBLE

        // Populate ListView
        if (lvNotes is androidx.recyclerview.widget.RecyclerView) {
            val adapter = lvNotes.adapter

            if (adapter is MyNoteRecyclerViewAdapter) {
                adapter.setItems(notes)
            }
        }
    }
}