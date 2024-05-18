package com.haton.walletwise.presentation.navigation.graph.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.haton.walletwise.presentation.screen.main.home.HomeScreen
import com.haton.walletwise.presentation.screen.main.home.HomeViewModel

private val home = HomeGraphDescription.Home.destination

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation(
        route = HomeNavGraph.Home.destination,
        startDestination = home
    ) {
        composable(home) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(homeViewModel)
        }
    }
}
