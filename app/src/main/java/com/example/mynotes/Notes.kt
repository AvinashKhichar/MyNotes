package com.example.mynotes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
data class Notes(
    val title: String,
    val content: String,
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
