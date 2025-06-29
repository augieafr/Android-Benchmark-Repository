package com.augieafr.benchmarkapp.data.repository

import com.augieafr.benchmarkapp.data.local.room.dao.NoteDao
import com.augieafr.benchmarkapp.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    /**
     * Generate and add 1000 random NoteEntity to the database
     */
    suspend fun addNotes() {
        val randomNotes = generateRandomNotes(1000)
        noteDao.insertNotes(randomNotes)
    }

    /**
     * Add a specific list of NoteEntity to the database
     * @param notes List of NoteEntity to be inserted
     */
    suspend fun addNotes(notes: List<NoteEntity>) {
        noteDao.insertNotes(notes)
    }

    /**
     * Generate random notes for testing/benchmarking
     * @param count Number of notes to generate
     * @return List of randomly generated NoteEntity
     */
    private fun generateRandomNotes(count: Int): List<NoteEntity> {
        val titles = listOf(
            "Meeting Notes",
            "Project Ideas",
            "Shopping List",
            "Daily Journal",
            "Book Review",
            "Travel Plans",
            "Recipe Collection",
            "Workout Routine",
            "Study Notes",
            "Budget Planning",
            "Goal Setting",
            "Movie Reviews",
            "Tech Notes",
            "Health Tips",
            "Creative Writing",
            "Business Ideas",
            "Learning Progress",
            "Event Planning",
            "Research Notes",
            "Personal Thoughts",
            "Code Snippets",
            "Design Inspiration",
            "Music Playlist",
            "Photo Memories",
            "Quote Collection",
            "Task Management",
            "Weekly Review",
            "Monthly Goals",
            "Annual Planning",
            "Dream Journal",
            "Habit Tracker",
            "Expense Report",
            "Time Management",
            "Skill Development",
            "Network Contacts",
            "Investment Ideas",
            "Home Improvement",
            "Garden Planning",
            "Cooking Experiments",
            "Art Projects",
            "Language Learning",
            "Fitness Goals",
            "Meditation Notes",
            "Reading List",
            "Podcast Notes",
            "Conference Summary",
            "Interview Prep",
            "Career Planning",
            "Side Projects",
            "Volunteer Work"
        )

        val descriptions = listOf(
            "This is a detailed note about important topics and ideas that need to be remembered for future reference.",
            "A comprehensive overview of the subject matter with key points highlighted for easy understanding.",
            "Quick notes taken during an important discussion or meeting with actionable items listed.",
            "Detailed analysis and thoughts on the current situation with potential solutions and next steps.",
            "Summary of research findings with relevant data points and conclusions drawn from the analysis.",
            "Personal reflections and insights gained from recent experiences and learning opportunities.",
            "Step-by-step instructions and guidelines for completing tasks efficiently and effectively.",
            "Collection of ideas and brainstorming results for future projects and creative endeavors.",
            "Important reminders and deadlines that need to be tracked and monitored regularly.",
            "Lessons learned from past experiences with recommendations for future improvements.",
            "Technical specifications and requirements for upcoming projects and implementations.",
            "Creative concepts and artistic inspiration gathered from various sources and experiences.",
            "Financial planning notes with budget allocations and expense tracking information.",
            "Health and wellness tips with exercise routines and nutritional guidelines for better living.",
            "Educational content and study materials for skill development and knowledge enhancement.",
            "Travel itinerary and planning details with accommodation and activity recommendations.",
            "Recipe ingredients and cooking instructions with preparation time and serving suggestions.",
            "Book summaries and key takeaways with personal opinions and rating assessments.",
            "Technology trends and innovations with potential impact on business and personal life.",
            "Goal setting framework with measurable objectives and timeline for achievement tracking."
        )

        return (1..count).map { index ->
            NoteEntity(
                title = "${titles.random()} #$index",
                description = descriptions.random() + " Additional context for note #$index with timestamp ${System.currentTimeMillis()}.",
                timestamp = System.currentTimeMillis() - (0..1000000).random() // Random timestamps within last ~16 minutes
            )
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
     * Delete all notes from the database
     */
    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    // Additional utility functions for completeness

    /**
     * Add a single note to the database
     * @param note NoteEntity to be inserted
     */
    suspend fun addNote(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    /**
     * Add a note with title and description (convenience method)
     * @param title Note title
     * @param description Note description
     */
    suspend fun addNote(title: String, description: String) {
        val note = NoteEntity(
            title = title,
            description = description
        )
        noteDao.insertNote(note)
    }

    /**
     * Get note by ID
     * @param id Note ID
     * @return NoteEntity or null if not found
     */
    suspend fun getNoteById(id: String): NoteEntity? {
        return noteDao.getNoteById(id)
    }

    /**
     * Update an existing note
     * @param note NoteEntity to be updated
     */
    suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    /**
     * Delete a specific note
     * @param note NoteEntity to be deleted
     */
    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    /**
     * Delete note by ID
     * @param id Note ID to be deleted
     */
    suspend fun deleteNoteById(id: String) {
        noteDao.deleteNoteById(id)
    }

    /**
     * Get total count of notes
     * @return Number of notes in database
     */
    suspend fun getNotesCount(): Int {
        return noteDao.getNotesCount()
    }

    /**
     * Search notes by query
     * @param query Search query for title or description
     * @return Flow of filtered notes
     */
    fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return noteDao.searchNotes(query)
    }
}