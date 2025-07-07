package com.augieafr.benchmark

import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.MemoryUsageMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseOperationBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    val noteCount = 10000

    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun databaseOperationPerformanceTest() = benchmarkRule.measureRepeated(
        packageName = "com.augieafr.benchmarkapp",
        metrics = listOf(MemoryUsageMetric(MemoryUsageMetric.Mode.Last)),
        iterations = 30,
        startupMode = StartupMode.WARM,
        setupBlock = {
            killProcess()
            startActivityAndWait()
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            // Navigate to Database Operation Test screen
            device.wait(Until.hasObject(By.text("Database Operation Test")), 5000)
            device.findObject(By.text("Database Operation Test"))?.click()

            // Wait for the screen to load
            device.wait(Until.hasObject(By.text("Database Status")), 5000)

            // Set notes count to 10000 (clear and type)
            val notesCountField = device.findObject(By.res("notes_count_input"))

            // Type 10000
            notesCountField.text = noteCount.toString()

            // Generate notes
            device.waitForIdle()
            device.findObject(By.text("Generate"))?.click()
            device.waitForIdle()

            // Wait for notes generation to complete (status should show "Ready to benchmark")
            device.wait(
                Until.hasObject(By.textContains("Ready to benchmark ($noteCount notes prepared)")),
                15000
            )
        }
    ) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Ensure we're ready to run benchmark
        device.wait(Until.hasObject(By.text("Run Full Database Benchmark")), 5000)

        // Start the database benchmark
        device.findObject(By.text("Run Full Database Benchmark"))?.click()

        // Wait for benchmark to complete (loading indicator should disappear)
        // The benchmark runs Insert -> Read -> Update -> Delete operations
        device.wait(Until.gone(By.text("Running database operations...")), 30000)

        // Verify benchmark results are displayed
        device.wait(Until.hasObject(By.text("Benchmark Results")), 5000)
    }
}