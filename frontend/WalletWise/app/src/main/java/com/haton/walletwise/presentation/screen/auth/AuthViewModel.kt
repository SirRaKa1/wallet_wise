package com.haton.walletwise.presentation.screen.auth

import android.util.Log
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
    private val _state: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.FirstEntryView)
    val state: MutableStateFlow<AuthState> = _state

    override fun send(event: AuthEvent) {
        when (val st = _state.value) {
            is AuthState.FirstEntryView -> reduce(event, st)
            is AuthState.SigInView -> reduce(event, st)
            is AuthState.SignUpView -> reduce(event, st)
        }
    }

    private fun reduce(
        event: AuthEvent,
        state: AuthState.FirstEntryView
    ) {
        when (event) {
            AuthEvent.EnterSignInView -> _state.value = AuthState.SigInView
            AuthEvent.EnterSignUoView -> _state.value = AuthState.SignUpView
            else -> Log.d("Debug", "AuthState.FirstEntryView - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: AuthEvent,
        state: AuthState.SigInView
    ) {
        when (event) {
            AuthEvent.EnterFirstEntryView -> _state.value = AuthState.FirstEntryView
            AuthEvent.EnterSignUoView -> _state.value = AuthState.SignUpView
            else -> Log.d("Debug", "AuthState.SigInView - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: AuthEvent,
        state: AuthState.SignUpView
    ) {
        when (event) {
            AuthEvent.EnterFirstEntryView -> _state.value = AuthState.FirstEntryView
            AuthEvent.EnterSignInView -> _state.value = AuthState.SigInView
            else -> Log.d("Debug", "AuthState.SignUpView - $event: ${_state.value}")
        }
    }
}
