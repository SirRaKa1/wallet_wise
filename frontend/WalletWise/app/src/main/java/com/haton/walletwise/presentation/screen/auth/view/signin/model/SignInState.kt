package com.haton.walletwise.presentation.screen.auth.view.signin.model

sealed class SignInState {
    data object Default : SignInState()
    data object SignInAttempt : SignInState()
    data object SignInSuccess : SignInState()
    data object SignInError : SignInState()
    data object SignUp : SignInState()
    data object ForgotPassword : SignInState()
}
