package com.augieafr.kmpbenchmarkapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.augieafr.kmpbenchmarkapp.ui.navigation.route.MainRoute
import com.augieafr.kmpbenchmarkapp.ui.screen.BenchmarkOptionScreen
import com.augieafr.kmpbenchmarkapp.ui.screen.animation_test.AnimationTestScreen
import com.augieafr.kmpbenchmarkapp.ui.screen.database_operation_test.DatabaseOperationTestScreen
import com.augieafr.kmpbenchmarkapp.ui.screen.scroll_test.ScrollTestScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
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

        composable<MainRoute.DatabaseOperationTest> {
            DatabaseOperationTestScreen(modifier)
        }

        composable<MainRoute.AnimationTest> {
            AnimationTestScreen(modifier)
        }
    }
}