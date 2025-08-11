package com.example.mynotes

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getNotesOrderedByTitle(): Flow<List<Notes>>

    @Query("SELECT * FROM notes ORDER BY date ASC")
    fun getNotesOrderedByDate(): Flow<List<Notes>>

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getNotesOrderedByID(): Flow<List<Notes>>
}