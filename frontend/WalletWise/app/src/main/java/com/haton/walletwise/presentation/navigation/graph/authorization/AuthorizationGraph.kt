package com.haton.walletwise.presentation.navigation.graph.authorization

import android.annotation.SuppressLint
import android.util.Log
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
    onSignIn: () -> Unit
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
                    pageNavigation(
                        navController = navController,
                        destination = sighIn,
                        incl = true
                    )
                },
                onSignUp = onSignIn
            )
        }
        composable(
            route = sighIn
        ) {
            val context = LocalContext.current
            val signInViewModel = hiltViewModel<SignInViewModel>()
            SignInView(
                signInViewModel = signInViewModel,
                onSignIn = { onSignIn() },
                onSignUp = {
                    pageNavigation2(
                        navController = navController,
                        destination = signUp,
                    )
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
                    pageNavigation2(
                        navController = navController,
                        destination = sighIn,
                    )
                },
                onSignUp = {
                    Toast.makeText(context, "Успешная регистрация", Toast.LENGTH_LONG).show()
                    pageNavigation2(
                        navController = navController,
                        destination = sighIn,
                    )
                }
            )
        }
    }
}

@SuppressLint("RestrictedApi")
private fun pageNavigation2(
    navController: NavController,
    destination: String = ""
) {
    val backStack = navController.currentBackStack.value
    val size = backStack.size
    val tail = arrayListOf<String>()
    for (i in 1 until size) {
        if (backStack[i].destination.route == destination) {
            var back = size - i - 1
            while (back != 0) {
                tail.add(backStack.last().destination.route!!)
                navController.popBackStack()
                back--
            }
            navController.popBackStack()
            while (tail.isNotEmpty()) {
                navController.navigate(tail.last())
                tail.removeLast()
            }
            navController.navigate(destination)
            return
        }
    }
    navController.navigate(destination)
}