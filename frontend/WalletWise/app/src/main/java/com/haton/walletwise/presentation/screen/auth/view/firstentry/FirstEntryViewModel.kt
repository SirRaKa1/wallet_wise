package com.haton.walletwise.presentation.screen.auth.view.firstentry

import android.util.Log
import androidx.lifecycle.ViewModel
import com.haton.walletwise.presentation.base.Event
import com.haton.walletwise.presentation.screen.auth.view.firstentry.model.FirstEntryEvent
import com.haton.walletwise.presentation.screen.auth.view.firstentry.model.FirstEntryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class FirstEntryViewModel @Inject constructor(
) : ViewModel(), Event<FirstEntryEvent> {
    private val _state: MutableStateFlow<FirstEntryState> =
        MutableStateFlow(FirstEntryState.Default)
    val state: MutableStateFlow<FirstEntryState> = _state

    override fun send(event: FirstEntryEvent) {
        when (val st = _state.value) {
            is FirstEntryState.Default -> reduce(event, st)
            is FirstEntryState.SignIn -> reduce(event, st)
            is FirstEntryState.SignUp -> reduce(event, st)
        }
    }

    private fun reduce(
        event: FirstEntryEvent,
        state: FirstEntryState.Default
    ) {
        when (event) {
            FirstEntryEvent.EnterSignIn -> _state.value = FirstEntryState.SignIn
            FirstEntryEvent.EnterSignUp -> _state.value = FirstEntryState.SignUp
            else -> Log.d("Debug", "FirstEntryState.Default - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: FirstEntryEvent,
        state: FirstEntryState.SignIn
    ) {
        when (event) {
            FirstEntryEvent.EnterFirstEntryView -> _state.value = FirstEntryState.Default
            else -> Log.d("Debug", "FirstEntryState.SignIn - $event: ${_state.value}")
        }
    }

    private fun reduce(
        event: FirstEntryEvent,
        state: FirstEntryState.SignUp
    ) {
        when (event) {
            FirstEntryEvent.EnterFirstEntryView -> _state.value = FirstEntryState.Default
            else -> Log.d("Debug", "FirstEntryState.SignUp - $event: ${_state.value}")
        }
    }
}
