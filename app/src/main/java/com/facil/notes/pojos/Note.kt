package com.facil.notes.pojos

class Note {
    var id: String = ""
    var title: String = ""
    var content: String = ""

    override fun toString(): String {
        return super.toString() + " with ID $id"
    }
}