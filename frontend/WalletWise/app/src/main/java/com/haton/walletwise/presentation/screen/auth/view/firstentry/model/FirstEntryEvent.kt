package com.haton.walletwise.presentation.screen.auth.view.firstentry.model

sealed class FirstEntryEvent {
    data object EnterFirstEntryView : FirstEntryEvent()
    data object EnterSignIn : FirstEntryEvent()
    data object EnterSignUp : FirstEntryEvent()
}