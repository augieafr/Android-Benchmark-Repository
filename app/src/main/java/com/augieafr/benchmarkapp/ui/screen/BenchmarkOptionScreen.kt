package com.augieafr.benchmarkapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.augieafr.benchmarkapp.ui.component.LargeSpace
import com.augieafr.benchmarkapp.ui.component.MyButton
import com.augieafr.benchmarkapp.ui.navigation.route.MainRoute

@Composable
fun BenchmarkOptionScreen(
    modifier: Modifier = Modifier,
    onNavigate: (MainRoute) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyButton(text = "Scroll Test") {
            onNavigate(MainRoute.ScrollTest)
        }
        LargeSpace()

        MyButton(text = "Database Operation Test") {

        }
        LargeSpace()
    }
}