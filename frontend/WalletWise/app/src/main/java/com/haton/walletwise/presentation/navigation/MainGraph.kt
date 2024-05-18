package com.haton.walletwise.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.haton.walletwise.presentation.screen.auth.AuthScreen
import com.haton.walletwise.presentation.screen.auth.AuthViewModel
import com.haton.walletwise.presentation.screen.auth.model.AuthEvent

private val authorization = MainGraphDestination.Authorization.destination

fun NavGraphBuilder.mainGraph(
    navController: NavController,
) {
    navigation(
        route = MainNavGraph.Main.destination,
        startDestination = authorization
    ) {
        composable(authorization) {
            val authViewModel = hiltViewModel<AuthViewModel>()
            AuthScreen(authViewModel)
        }
    }
}
