package com.haton.walletwise.presentation.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.haton.walletwise.presentation.animation.SlideHorizontally
import com.haton.walletwise.presentation.navigation.graph.authorization.AuthorizationNavGraph
import com.haton.walletwise.presentation.navigation.graph.authorization.authorizationGraph

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel
) {
    val authNavController = rememberNavController()
    val navAnimation = SlideHorizontally()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = authNavController,
            startDestination = AuthorizationNavGraph.Authorization.destination,
            exitTransition = { navAnimation.exitTransitionSlideOut() },
        ) {
            authorizationGraph(authNavController)
        }
    }
}
