package com.example.easynote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int?

) {

    constructor(title: String, description: String, priority: Int?) : this(
        1,
        title,
        description,
        priority
    )
}
