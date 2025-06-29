package com.augieafr.benchmarkapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.augieafr.benchmarkapp.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: String): NoteEntity?

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchQuery || '%' OR description LIKE '%' || :searchQuery || '%' ORDER BY timestamp DESC")
    fun searchNotes(searchQuery: String): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: List<NoteEntity>)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: String)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Query("SELECT COUNT(*) FROM notes")
    suspend fun getNotesCount(): Int

    @Query("SELECT * FROM notes ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getRecentNotes(limit: Int): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE timestamp BETWEEN :startTime AND :endTime ORDER BY timestamp DESC")
    fun getNotesByDateRange(startTime: Long, endTime: Long): Flow<List<NoteEntity>>
}
