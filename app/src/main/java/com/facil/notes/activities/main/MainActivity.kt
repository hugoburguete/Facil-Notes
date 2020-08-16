package com.facil.notes.activities.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.view.Menu
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
import java.lang.Exception

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainActivityContract.View, NoteEditorContract.OnNoteLoadFailureListener,
    NoteEditorContract.OnNoteSavedListener {

    private val notesListFragment = NotesListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = MainPresenter(this, NotesRepository())

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState != null) {
            return
        }

        presenter.loadNotes()
        notesListFragment.presenter = presenter
        inflateFragment(R.id.fl_notes_list, notesListFragment)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
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
            inflateFragment(R.id.fl_notes_list, noteEditorFragment)
        } else {
            val intent = Intent(this, NoteEditorActivity::class.java).apply {
                putExtra("noteId", note.id)
            }

            println("starting activity")
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
