package com.augieafr.benchmarkapp.ui.screen.database_operation_test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.augieafr.benchmarkapp.data.local.room.AppDatabase
import com.augieafr.benchmarkapp.data.model.entity.NoteEntity
import com.augieafr.benchmarkapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class DatabaseBenchmarkResult(
    val operation: String,
    val duration: Long,
    val recordCount: Int,
    val status: String
)

class DatabaseOperationTestViewModel(
    application: Application
) : AndroidViewModel(application) {

    // Create NoteRepository inside the class
    private val database = AppDatabase.getDatabase(application)
    private val noteRepository = NoteRepository(database.noteDao())

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _benchmarkResults = MutableStateFlow<List<DatabaseBenchmarkResult>>(emptyList())
    val benchmarkResults = _benchmarkResults.asStateFlow()

    private val _totalNotes = MutableStateFlow(0)
    val totalNotes = _totalNotes.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _randomNotesCount = MutableStateFlow(1000)
    val randomNotesCount = _randomNotesCount.asStateFlow()

    private val _notesGenerated = MutableStateFlow(false)
    val notesGenerated = _notesGenerated.asStateFlow()

    private val listNote = mutableListOf<NoteEntity>()

    init {
        viewModelScope.launch {
            noteRepository.deleteAllNotes()
        }
    }

    /**
     * Run comprehensive database benchmark test
     */
    fun runDatabaseBenchmark() = viewModelScope.launch {
        _isLoading.value = true
        _errorMessage.value = null
        val results = mutableListOf<DatabaseBenchmarkResult>()

        try {
            // Test 1: Insert notes
            val insertResult = benchmarkInsertOperation()
            results.add(insertResult)

            // Test 2: Read all notes
            val readResult = benchmarkReadOperation()
            results.add(readResult)

            // Test 5: Delete all notes
            val deleteResult = benchmarkDeleteOperation()
            results.add(deleteResult)

            _benchmarkResults.value = results

        } catch (e: Exception) {
            _errorMessage.value = "Benchmark failed: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }

    // Private benchmark methods
    private suspend fun benchmarkInsertOperation(): DatabaseBenchmarkResult {
        val startTime = System.currentTimeMillis()
        noteRepository.addNotes(listNote) // Insert random notes
        val duration = System.currentTimeMillis() - startTime

        return DatabaseBenchmarkResult(
            operation = "Insert ${listNote.size} Notes",
            duration = duration,
            recordCount = listNote.size,
            status = "Success"
        )
    }

    private suspend fun benchmarkReadOperation(): DatabaseBenchmarkResult {
        val startTime = System.currentTimeMillis()
        val notes = noteRepository.getAllNotes().first()
        val duration = System.currentTimeMillis() - startTime

        return DatabaseBenchmarkResult(
            operation = "Read All Notes",
            duration = duration,
            recordCount = notes.size,
            status = "Success"
        )
    }

    private suspend fun benchmarkDeleteOperation(): DatabaseBenchmarkResult {
        val startTime = System.currentTimeMillis()
        noteRepository.deleteAllNotes()
        val duration = System.currentTimeMillis() - startTime

        return DatabaseBenchmarkResult(
            operation = "Delete All Notes",
            duration = duration,
            recordCount = 0,
            status = "Success"
        )
    }

    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }

    /**
     * Update the count of random notes to be generated
     */
    fun updateRandomNotesCount(count: Int) {
        _randomNotesCount.value = count.coerceAtLeast(1) // Ensure minimum of 1
        _notesGenerated.value = false // Reset generated status when count changes
    }

    /**
     * Generate random notes with the specified count
     */
    fun generateRandomNotes() {
        setRandomNotes(_randomNotesCount.value)
    }

    private fun setRandomNotes(count: Int) {
        _isLoading.update {
            true
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                listNote.clear()
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

                (1..count).map { index ->
                    NoteEntity(
                        title = "${titles.random()} #$index",
                        description = descriptions.random() + " Additional context for note #$index with timestamp ${System.currentTimeMillis()}.",
                        timestamp = System.currentTimeMillis()
                    )
                }.run {
                    listNote.addAll(this)
                    _notesGenerated.update { true } // Mark notes as generated
                    _isLoading.update {
                        false
                    }
                }
            }
        }
    }
}