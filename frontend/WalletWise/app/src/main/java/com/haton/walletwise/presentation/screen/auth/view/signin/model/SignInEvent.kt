package com.haton.walletwise.presentation.screen.auth.view.signin.model

sealed class SignInEvent {
    data object EnterView : SignInEvent()
    data object SignUp : SignInEvent()
    data class SignIn(
        val login: String,
        val password: String
    ) : SignInEvent()

    data object ForgotPassword : SignInEvent()
    data object SignInSuccess : SignInEvent()
    data object SignInError : SignInEvent()
}
