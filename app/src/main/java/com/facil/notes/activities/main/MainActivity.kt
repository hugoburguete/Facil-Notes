package com.facil.notes.activities.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.facil.notes.R
import com.facil.notes.activities.note_editor.NoteEditorActivity
import com.facil.notes.fragments.NoteEditorFragment
import com.facil.notes.fragments.NotesListFragment
import com.facil.notes.framework.BaseActivity
import com.facil.notes.pojos.Note
import com.facil.notes.presenters.MainActivityContract
import com.facil.notes.presenters.NoteEditorContract
import com.facil.notes.repositories.NotesRepository

class MainActivity : BaseActivity(),
    MainActivityContract.View, NoteEditorContract.OnNoteLoadFailureListener,
    NoteEditorContract.OnNoteSavedListener {

    private val notesListFragment = NotesListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = MainPresenter(this, NotesRepository())

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (savedInstanceState != null) {
            return
        }

        presenter.loadNotes()
        notesListFragment.presenter = presenter
        inflateFragment(R.id.fl_notes_list, notesListFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNotesLoaded(notes: ArrayList<Note>) {
        notesListFragment.onNotesLoaded(notes)
    }

    override fun onNoteSelected(note: Note) {
        val flNoteEditor = findViewById<FrameLayout>(R.id.fl_note_editor)
        if (flNoteEditor != null) {
            val noteEditorFragment = NoteEditorFragment()
            val bundle = Bundle()
            bundle.putString("noteId", note.id)
            noteEditorFragment.arguments = bundle
            inflateFragment(R.id.fl_note_editor, noteEditorFragment)
        } else {
            val intent = Intent(this, NoteEditorActivity::class.java).apply {
                putExtra("noteId", note.id)
            }

            startActivity(intent)
        }
    }

    override fun onNoteLoadFailure(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun onNoteSaved() {
        TODO("Not yet implemented")
    }

    override fun onNoteSaveFailure(e: Exception) {
        TODO("Not yet implemented")
    }
}
