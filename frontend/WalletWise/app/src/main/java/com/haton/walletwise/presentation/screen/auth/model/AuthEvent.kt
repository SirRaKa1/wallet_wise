package com.haton.walletwise.presentation.screen.auth.model

sealed class AuthEvent {
    data object EnterHomeScreen : AuthEvent()
}