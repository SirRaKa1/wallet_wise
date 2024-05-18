package com.haton.walletwise.presentation.screen.auth.view.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haton.walletwise.presentation.base.Event
import com.haton.walletwise.presentation.screen.auth.view.signin.model.SignInEvent
import com.haton.walletwise.presentation.screen.auth.view.signup.model.SignUpEvent
import com.haton.walletwise.presentation.screen.auth.view.signup.model.SignUpState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
) : ViewModel(), Event<SignUpEvent> {
    private val _state: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.Default)
    val state: MutableStateFlow<SignUpState> = _state

    override fun send(event: SignUpEvent) {
        when (val st = _state.value) {
            is SignUpState.Default -> reduce(event, st)
            is SignUpState.SignUpAttempt -> reduce(event, st)
            is SignUpState.SignUpError -> reduce(event, st)
            is SignUpState.SignUpSuccess -> reduce(event, st)
            is SignUpState.SignIn -> reduce(event, st)
        }
    }

    private fun reduce(
        event: SignUpEvent,
        state: SignUpState.Default
    ) {
        when (event) {
            SignUpEvent.EnterView -> _state.value = SignUpState.Default
            SignUpEvent.SignIn -> {
                _state.value = SignUpState.SignIn
            }

            is SignUpEvent.SignUpAttempt -> {
                _state.value = SignUpState.SignUpAttempt
                viewModelScope.launch(Dispatchers.IO) {
                    delay(3000)
                    send(SignUpEvent.SignUpSuccess)
                }
            }

            else -> Log.d("Debug", "SignUpState.Default - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignUpEvent,
        state: SignUpState.SignIn
    ) {
        when (event) {
            SignUpEvent.EnterView -> _state.value = SignUpState.Default

            else -> Log.d("Debug", "SignUpState.SignIn - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignUpEvent,
        state: SignUpState.SignUpAttempt
    ) {
        when (event) {
            SignUpEvent.EnterView -> _state.value = SignUpState.Default
            SignUpEvent.SignUpError -> {
                _state.value = SignUpState.SignUpError
            }

            SignUpEvent.SignUpSuccess -> {
                _state.value = SignUpState.SignUpSuccess
            }

            else -> Log.d("Debug", "SignUpState.SignUpAttempt - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignUpEvent,
        state: SignUpState.SignUpSuccess
    ) {
        when (event) {
            SignUpEvent.EnterView -> _state.value = SignUpState.Default
            else -> Log.d("Debug", "SignUpState.SignUpSuccess - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignUpEvent,
        state: SignUpState.SignUpError
    ) {
        when (event) {
            SignUpEvent.EnterView -> _state.value = SignUpState.Default
            SignUpEvent.SignIn -> {
                _state.value = SignUpState.SignIn
            }

            is SignUpEvent.SignUpAttempt -> {
                _state.value = SignUpState.SignUpAttempt
            }

            else -> Log.d("Debug", "SignUpState.SignUpError - $event: ${_state.value}")
        }
    }
}
