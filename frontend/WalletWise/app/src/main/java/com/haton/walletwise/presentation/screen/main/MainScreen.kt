package com.haton.walletwise.presentation.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.haton.walletwise.presentation.animation.SlideHorizontally
import com.haton.walletwise.presentation.navigation.graph.main.HomeNavGraph
import com.haton.walletwise.presentation.navigation.graph.main.homeGraph

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
    val mainNavController = rememberNavController()
    val navAnimation = SlideHorizontally()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = mainNavController,
            startDestination = HomeNavGraph.Home.destination,
            exitTransition = { navAnimation.exitTransitionSlideOut() },
        ) {
            homeGraph(mainNavController)
        }
    }
}
