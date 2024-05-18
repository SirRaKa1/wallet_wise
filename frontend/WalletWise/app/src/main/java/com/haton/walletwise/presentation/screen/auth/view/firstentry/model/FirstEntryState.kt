package com.haton.walletwise.presentation.screen.auth.view.firstentry.model

sealed class FirstEntryState {
    data object Default : FirstEntryState()
    data object SignIn : FirstEntryState()
    data object SignUp : FirstEntryState()
}
