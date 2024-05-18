package com.haton.walletwise.presentation.screen.auth.view.signup.model

sealed class SignUpEvent {
    data object EnterView : SignUpEvent()
    data object SignIn : SignUpEvent()
    data class SignUpAttempt(
        val userName: String,
        val login: String,
        val password: String
    ) : SignUpEvent()

    data object SignUpSuccess : SignUpEvent()
    data object SignUpError : SignUpEvent()
}
