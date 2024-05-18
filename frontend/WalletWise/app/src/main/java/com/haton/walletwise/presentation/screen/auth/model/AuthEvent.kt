package com.haton.walletwise.presentation.screen.auth.model

sealed class AuthEvent {
    data object EnterFirstEntryView : AuthEvent()
    data object EnterSignInView : AuthEvent()
    data object EnterSignUoView : AuthEvent()
}