package com.augieafr.benchmarkapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.augieafr.benchmarkapp.ui.navigation.route.MainRoute
import com.augieafr.benchmarkapp.ui.screen.BenchmarkOptionScreen
import com.augieafr.benchmarkapp.ui.screen.scroll_test.ScrollTestScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    setTitle: (String) -> Unit
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = MainRoute.BenchmarkOption
    ) {
        composable<MainRoute.BenchmarkOption> {
            BenchmarkOptionScreen(modifier) {
                navController.navigate(it)
            }
        }

        composable<MainRoute.ScrollTest> {
            ScrollTestScreen(modifier)
        }
    }
}