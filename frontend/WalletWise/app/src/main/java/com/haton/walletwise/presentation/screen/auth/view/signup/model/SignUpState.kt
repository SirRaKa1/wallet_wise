package com.haton.walletwise.presentation.screen.auth.view.signup.model

sealed class SignUpState {
    data object Default : SignUpState()
    data object SignUpAttempt : SignUpState()
    data object SignUpSuccess : SignUpState()
    data object SignUpError : SignUpState()
    data object SignIn : SignUpState()
}
