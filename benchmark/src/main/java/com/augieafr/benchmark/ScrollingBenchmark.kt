package com.augieafr.benchmark

import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MemoryUsageMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrollingBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun scrollingPerformanceTest() = benchmarkRule.measureRepeated(
        packageName = "com.augieafr.kmpbenchmarkapp",
        metrics = listOf(FrameTimingMetric(), MemoryUsageMetric(MemoryUsageMetric.Mode.Last)),
        iterations = 30,
        startupMode = StartupMode.WARM,
        setupBlock = {
            killProcess()
            startActivityAndWait()
            device.wait(Until.hasObject(By.text("Scroll Test")), 5000)
            device.findObject(By.text("Scroll Test"))?.click()
        }
    ) {
        // wait for loading indicator to disappear
        device.wait(Until.gone(By.res("loading_indicator")), 5000)

        // scroll to bottom twice
        val scrollContainer = device.findObject(By.res("scroll_container"))
        repeat(3) {
            scrollContainer.fling(Direction.DOWN)
        }
    }
}