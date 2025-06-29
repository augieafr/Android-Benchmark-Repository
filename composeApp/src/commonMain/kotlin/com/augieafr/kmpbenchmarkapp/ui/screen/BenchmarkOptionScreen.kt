package com.augieafr.kmpbenchmarkapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.augieafr.benchmarkapp.ui.component.LargeSpace
import com.augieafr.benchmarkapp.ui.component.MyButton
import com.augieafr.kmpbenchmarkapp.ui.navigation.route.MainRoute

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
        MyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Scroll Test"
        ) {
            onNavigate(MainRoute.ScrollTest)
        }
        LargeSpace()

        MyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Database Operation Test"
        ) {
            onNavigate(MainRoute.DatabaseOperationTest)
        }
        LargeSpace()

        MyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Animation Test"
        ) {
            onNavigate(MainRoute.AnimationTest)
        }
        LargeSpace()
    }
}