package com.haton.walletwise.presentation.screen.auth

import androidx.lifecycle.ViewModel
import com.haton.walletwise.presentation.base.Event
import com.haton.walletwise.presentation.screen.auth.model.AuthEvent
import com.haton.walletwise.presentation.screen.auth.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
) : ViewModel(), Event<AuthEvent> {
    private val _state: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Default)
    val state: MutableStateFlow<AuthState> = _state

    override fun send(event: AuthEvent) {
        when (val st = _state.value) {
            is AuthState.Default -> reduce(event, st)
            is AuthState.HomeScreen -> {}
        }
    }

    private fun reduce(
        event: AuthEvent,
        state: AuthState.Default
    ) {
        when (event) {
            AuthEvent.EnterHomeScreen -> _state.value = AuthState.HomeScreen
        }
    }
}
