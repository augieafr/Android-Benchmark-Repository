package com.augieafr.kmpbenchmarkapp.data.repository

import androidx.compose.ui.util.trace
import com.augieafr.kmpbenchmarkapp.data.local.room.dao.NoteDao
import com.augieafr.kmpbenchmarkapp.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    /**
     * Add a specific list of NoteEntity to the database
     * @param notes List of NoteEntity to be inserted
     */
    suspend fun addNotes(notes: List<NoteEntity>) {
        trace("addNotes") {
            noteDao.insertNotes(notes)
        }
    }

    /**
     * Read all notes from the database
     * @return Flow of list of all NoteEntity ordered by timestamp (newest first)
     */
    fun getAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

    /**
     * Update a specific list of NoteEntity in the database
     * @param notes List of NoteEntity to be updated
     */
    suspend fun updateNotes(notes: List<NoteEntity>) {
        noteDao.updateNotes(notes)
    }

    /**
     * Delete all notes from the database
     */
    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

}