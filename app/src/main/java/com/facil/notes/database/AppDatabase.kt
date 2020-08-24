package com.facil.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.facil.notes.pojos.Note
import com.facil.notes.pojos.NoteTagRelation
import com.facil.notes.pojos.Tag

@Database(entities = [Note::class, Tag::class, NoteTagRelation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NotesDao

    abstract fun tagsDao(): TagsDao

    abstract fun noteTagRelationDao(): NoteTagRelationDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "facil-notes.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}