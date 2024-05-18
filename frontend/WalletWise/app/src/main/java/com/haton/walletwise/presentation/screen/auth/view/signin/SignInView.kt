package com.haton.walletwise.presentation.screen.auth.view.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haton.walletwise.R
import com.haton.walletwise.presentation.screen.auth.view.signin.model.SignInEvent
import com.haton.walletwise.presentation.screen.auth.view.signin.model.SignInState
import com.haton.walletwise.presentation.screen.auth.view.signup.model.SignUpState
import com.haton.walletwise.presentation.screen.widgets.TextFieldView
import com.haton.walletwise.presentation.ui.theme.WiseCommon

@Composable
fun SignInView(
    signInViewModel: SignInViewModel,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
) {
    val state = signInViewModel.state.collectAsState()
    val context = LocalContext.current
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    LaunchedEffect(state.value) {
        when (state.value) {
            SignInState.Default -> {
            }

            SignInState.ForgotPassword -> {
                onForgotPassword()
                signInViewModel.send(SignInEvent.EnterView)
            }

            SignInState.SignInAttempt -> {
            }

            SignInState.SignInError -> {
            }

            SignInState.SignInSuccess -> {
                onSignIn()
                signInViewModel.send(SignInEvent.EnterView)
            }

            SignInState.SignUp -> {
                onSignUp()
                signInViewModel.send(SignInEvent.EnterView)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WiseCommon.colors.background)) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(top = (prop * 120).dp),
                text = context.getString(R.string.authorization__authorization),
                style = WiseCommon.typography.appTitle.copy(fontSize = (prop * 24).sp)
            )
        }
        TextFieldView(
            placeholder = context.getString(R.string.authorization__login),
            value = login,
            shape = WiseCommon.shape.big,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (prop * 20).dp, end = (prop * 20).dp, top = (prop * 20).dp)
        )
        TextFieldView(
            placeholder = context.getString(R.string.authorization__pass),
            value = password,
            shape = WiseCommon.shape.big,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (prop * 20).dp, end = (prop * 20).dp, top = (prop * 8).dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (prop * 20).dp, end = (prop * 20).dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextButton(
                onClick = {
                    signInViewModel.send(SignInEvent.ForgotPassword)
                },
            ) {
                Text(
                    text = context.getString(R.string.authorization__forgot_password),
                    style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 16).sp)
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(top = (prop * 32).dp, start = (prop * 20).dp, end = (prop * 20).dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = (prop * 12).dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WiseCommon.colors.primary,
                contentColor = WiseCommon.colors.blackText
            ),
            shape = WiseCommon.shape.big,
            onClick = {
                signInViewModel.send(SignInEvent.SignIn(login.value, password.value))
            }
        ) {
            Text(
                text = context.getString(R.string.authorization__sign_in),
                style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 16).sp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (prop * 20).dp, end = (prop * 20).dp),
            contentAlignment = Alignment.Center
        ) {
            TextButton(onClick = {
                signInViewModel.send(SignInEvent.SignUp)
            }) {
                Text(
                    text = context.getString(R.string.authorization__create_acc),
                    style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 16).sp)
                )
            }
        }
    }

    when (state.value) {
        SignInState.SignInAttempt -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {  }
                    .background(WiseCommon.colors.black.copy(alpha = 0.5f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width((prop * 64).dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        else -> {}
    }
}