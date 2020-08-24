package com.facil.notes

import com.facil.notes.pojos.Note
import com.facil.notes.repositories.NoteRepositoryContract
import com.facil.notes.ui.fragments.note_editor.NoteEditorContract
import com.facil.notes.ui.fragments.note_editor.NoteEditorPresenter
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
class NoteEditorPresenterTest {
    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var repository: NoteRepositoryContract

    @Mock
    lateinit var view: NoteEditorContract.View

    private lateinit var presenter: NoteEditorPresenter

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        presenter = NoteEditorPresenter(view, repository)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `should load a note`() {
        val expectedNote = Note()
        expectedNote.id = 1
        runBlocking {
            Mockito.`when`(repository.getNote(1)).thenReturn(expectedNote)
            presenter.loadNote(1)
            Mockito.verify(view).onNoteLoaded(expectedNote)
        }
    }
}