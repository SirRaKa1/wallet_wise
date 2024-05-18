package com.haton.walletwise.presentation.navigation.graph.authorization

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.haton.walletwise.presentation.animation.SlideHorizontally
import com.haton.walletwise.presentation.navigation.utils.pageNavigation
import com.haton.walletwise.presentation.screen.auth.view.firstentry.FirstEntryView
import com.haton.walletwise.presentation.screen.auth.view.firstentry.FirstEntryViewModel
import com.haton.walletwise.presentation.screen.auth.view.signin.SignInView
import com.haton.walletwise.presentation.screen.auth.view.signin.SignInViewModel
import com.haton.walletwise.presentation.screen.auth.view.signup.SignUpView
import com.haton.walletwise.presentation.screen.auth.view.signup.SignUpViewModel

private val firstEntry = AuthorizationGraphDestination.FirstEntry.destination
private val sighIn = AuthorizationGraphDestination.SignIp.destination
private val signUp = AuthorizationGraphDestination.SignUp.destination

fun NavGraphBuilder.authorizationGraph(
    navController: NavController,
) {
    navigation(
        route = AuthorizationNavGraph.Authorization.destination,
        startDestination = firstEntry
    ) {
        val navAnimation = SlideHorizontally()
        composable(
            route = firstEntry,
            exitTransition = { navAnimation.exitTransitionSlideOut() }
        ) {
            val firstEntryViewModel = hiltViewModel<FirstEntryViewModel>()
            FirstEntryView(
                firstEntryViewModel = firstEntryViewModel,
                onSignIn = {
                    navController.navigate(sighIn){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onSignUp = {
                    navController.navigate(sighIn){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = sighIn
        ) {
            val context = LocalContext.current
            val signInViewModel = hiltViewModel<SignInViewModel>()
            SignInView(
                signInViewModel = signInViewModel,
                onSignIn = {
                    Toast.makeText(context, "Успешный вход", Toast.LENGTH_LONG).show()
                },
                onSignUp = {
                    navController.navigate(signUp){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onForgotPassword = {
                    Toast.makeText(context, "Пароль восстановлен", Toast.LENGTH_LONG).show()
                }
            )
        }
        composable(
            route = signUp
        ) {
            val context = LocalContext.current
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            SignUpView(
                signUpViewModel = signUpViewModel,
                onSignIn = {
                    navController.navigate(sighIn){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onSignUp = {
                    Toast.makeText(context, "Успешная регистрация", Toast.LENGTH_LONG).show()
                    navController.navigate(sighIn){
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
