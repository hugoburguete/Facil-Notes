package com.facil.notes

import com.facil.notes.pojos.Note
import com.facil.notes.repositories.NoteRepositoryContract
import com.facil.notes.ui.fragments.note_list.NoteListContract
import com.facil.notes.ui.fragments.note_list.NoteListPresenter
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NoteListPresenterTest {
    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var repository: NoteRepositoryContract

    @Mock
    lateinit var view: NoteListContract.View

    private lateinit var presenter: NoteListContract.Presenter

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        presenter = NoteListPresenter(view, repository)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `should load notes`() {
        val expectedNotes = getNotesList()
        runBlocking {
            Mockito.`when`(repository.getNotes()).thenReturn(expectedNotes)
        }

        presenter.loadNotes()
        Mockito.verify(view).onNotesLoading()
        Mockito.verify(view).onNotesLoaded(expectedNotes)
    }

    private fun getNotesList(): ArrayList<Note> {
        val noteList = ArrayList<Note>()
        var i = 0
        while (i < 20) {
            val note = Note()
            note.id = i.toString()
            note.title = "Note $i"
            noteList.add(note)
            i++
        }
        return noteList
    }
}