package com.augieafr.benchmarkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.augieafr.benchmarkapp.ui.navigation.MainNavHost
import com.augieafr.benchmarkapp.ui.navigation.route.MainRoute
import com.augieafr.benchmarkapp.ui.navigation.route.route
import com.augieafr.benchmarkapp.ui.theme.BenchmarkAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BenchmarkAppTheme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                var title: String? by remember { mutableStateOf("Benchmark App") }

                // Update title based on current destination
                LaunchedEffect(currentBackStackEntry) {
                    val route = currentBackStackEntry?.destination?.route
                    title = when (route) {
                        MainRoute.BenchmarkOption.route -> "Benchmark App"
                        MainRoute.ScrollTest.route -> "Scroll Test"
                        MainRoute.DatabaseOperationTest.route -> "Database Operation Test"
                        MainRoute.AnimationTest.route -> null
                        else -> "Benchmark App"
                    }
                }

                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .semantics {
                        testTagsAsResourceId = true
                    }, topBar = {
                    title?.let {
                        TopAppBar(title = {
                            Text(it)
                        })
                    }
                }) { innerPadding ->
                    MainNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController = navController
                    ) {
                        // This setTitle function is now optional since we handle it above
                        title = it
                    }
                }
            }
        }
    }
}
