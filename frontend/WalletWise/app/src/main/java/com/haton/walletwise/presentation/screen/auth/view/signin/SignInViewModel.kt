package com.haton.walletwise.presentation.screen.auth.view.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haton.walletwise.presentation.base.Event
import com.haton.walletwise.presentation.screen.auth.view.signin.model.SignInEvent
import com.haton.walletwise.presentation.screen.auth.view.signin.model.SignInState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
) : ViewModel(), Event<SignInEvent> {
    private val _state: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState.Default)
    val state: MutableStateFlow<SignInState> = _state

    override fun send(event: SignInEvent) {
        when (val st = _state.value) {
            is SignInState.Default -> reduce(event, st)
            is SignInState.SignInAttempt -> reduce(event, st)
            is SignInState.SignInError -> reduce(event, st)
            is SignInState.SignInSuccess -> reduce(event, st)
            is SignInState.SignUp -> reduce(event, st)
            is SignInState.ForgotPassword -> reduce(event, st)
        }
    }

    private fun reduce(
        event: SignInEvent,
        state: SignInState.Default
    ) {
        when (event) {
            SignInEvent.EnterView -> _state.value = SignInState.Default
            SignInEvent.SignUp -> {
                _state.value = SignInState.SignUp
            }

            SignInEvent.ForgotPassword -> {
                _state.value = SignInState.ForgotPassword
            }

            is SignInEvent.SignIn -> {
                _state.value = SignInState.SignInAttempt
                viewModelScope.launch(Dispatchers.IO) {
                    delay(3000)
                    send(SignInEvent.SignInSuccess)
                }
            }

            else -> Log.d("Debug", "SignInState.Default - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignInEvent,
        state: SignInState.SignInAttempt
    ) {
        when (event) {
            SignInEvent.EnterView -> _state.value = SignInState.Default
            SignInEvent.SignInSuccess -> {
                _state.value = SignInState.SignInSuccess
            }

            SignInEvent.SignInError -> {
                _state.value = SignInState.SignInError
            }

            else -> Log.d("Debug", "SignInState.LoginAttempt - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignInEvent,
        state: SignInState.SignInSuccess
    ) {
        when (event) {
            SignInEvent.EnterView -> _state.value = SignInState.Default
            SignInEvent.SignUp -> {
                _state.value = SignInState.SignUp
            }

            SignInEvent.ForgotPassword -> {
                _state.value = SignInState.ForgotPassword
            }

            is SignInEvent.SignIn -> {
                _state.value = SignInState.SignInAttempt
            }

            else -> Log.d("Debug", "SignInState.SignInSuccess - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignInEvent,
        state: SignInState.SignInError
    ) {
        when (event) {
            SignInEvent.EnterView -> _state.value = SignInState.Default
            SignInEvent.SignUp -> {
                _state.value = SignInState.SignUp
            }

            SignInEvent.ForgotPassword -> {
                _state.value = SignInState.ForgotPassword
            }

            is SignInEvent.SignIn -> {
                _state.value = SignInState.SignInAttempt
            }

            else -> Log.d("Debug", "SignInState.SignInError - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignInEvent,
        state: SignInState.SignUp
    ) {
        when (event) {
            SignInEvent.EnterView -> {
                _state.value = SignInState.Default
            }

            else -> Log.d("Debug", "SignInState.ForgotPassword - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: SignInEvent,
        state: SignInState.ForgotPassword
    ) {
        when (event) {
            SignInEvent.EnterView -> _state.value = SignInState.Default
            SignInEvent.EnterView -> {
                _state.value = SignInState.Default
            }

            else -> Log.d("Debug", "SignInState.ForgotPassword - $event: ${_state.value}")
        }
    }
}
