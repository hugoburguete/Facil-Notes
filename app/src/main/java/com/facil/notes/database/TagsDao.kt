package com.facil.notes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.facil.notes.pojos.Tag

@Dao
interface TagsDao {
    @Query("SELECT * from tags")
    fun getAll(): List<Tag>

    @Query("SELECT * from tags WHERE id = :id")
    fun findById(id: Int): Tag

    @Insert
    fun insertAll(vararg tags: Tag): List<Long>

    @Delete
    fun delete(tag: Tag)
}