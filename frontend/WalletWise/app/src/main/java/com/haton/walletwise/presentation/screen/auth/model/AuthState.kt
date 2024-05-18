package com.haton.walletwise.presentation.screen.auth.model

sealed class AuthState {
    /**
     * View
     */
    data object FirstEntryView : AuthState()
    data object SigInView : AuthState()
    data object SignUpView : AuthState()
}
