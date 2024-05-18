package com.haton.walletwise.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.haton.walletwise.presentation.navigation.utils.pageNavigation
import com.haton.walletwise.presentation.screen.auth.AuthScreen
import com.haton.walletwise.presentation.screen.auth.AuthViewModel
import com.haton.walletwise.presentation.screen.auth.model.AuthState
import com.haton.walletwise.presentation.screen.main.MainScreen
import com.haton.walletwise.presentation.screen.main.MainViewModel

private val authorization = MainGraphDestination.Authorization.destination
private val main = MainGraphDestination.Main.destination

fun NavGraphBuilder.mainGraph(
    navController: NavController,
) {
    navigation(
        route = MainNavGraph.Main.destination,
        startDestination = authorization
    ) {
        composable(authorization) {
            val authViewModel = hiltViewModel<AuthViewModel>()
            val state = authViewModel.state.collectAsState()
            when (state.value) {
                AuthState.HomeScreen -> {
                    pageNavigation(
                        navController = navController,
                        destination = main,
                        incl = true
                    )
                }

                else -> {}
            }
            AuthScreen(authViewModel)
        }
        composable(main) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(mainViewModel)
        }
    }
}
