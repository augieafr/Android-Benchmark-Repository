package com.augieafr.benchmark

import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
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
class AnimationBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun animationPerformanceTest() = benchmarkRule.measureRepeated(
        packageName = "com.augieafr.benchmarkapp",
        metrics = listOf(FrameTimingMetric(), MemoryUsageMetric(MemoryUsageMetric.Mode.Last)),
        iterations = 30,
        startupMode = StartupMode.WARM,
        setupBlock = {
            killProcess()
            startActivityAndWait()
            device.wait(Until.hasObject(By.text("Animation Test")), 5000)
            device.findObject(By.text("Animation Test"))?.click()
        }
    ) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.wait(Until.hasObject(By.text("Start Animation Performance Test")), 5000)
        // Start the animation
        device.findObject(By.text("Start Animation Performance Test"))?.click()
        device.wait(Until.hasObject(By.text("Complete")), 5000)
    }
}
