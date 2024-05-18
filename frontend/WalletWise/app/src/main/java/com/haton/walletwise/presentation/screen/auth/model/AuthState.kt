package com.haton.walletwise.presentation.screen.auth.model

sealed class AuthState {
    data object Default : AuthState()
    data object HomeScreen : AuthState()
}
