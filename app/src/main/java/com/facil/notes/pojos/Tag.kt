package com.facil.notes.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var name: String = "",
    var colour: String? = null
)