package com.haton.walletwise.presentation.screen.auth.view.firstentry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haton.walletwise.R
import com.haton.walletwise.presentation.screen.auth.view.firstentry.model.FirstEntryEvent
import com.haton.walletwise.presentation.screen.auth.view.firstentry.model.FirstEntryState
import com.haton.walletwise.presentation.ui.theme.WiseCommon

@Composable
fun FirstEntryView(
    firstEntryViewModel: FirstEntryViewModel,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
) {
    val state = firstEntryViewModel.state.collectAsState()
    val context = LocalContext.current
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    LaunchedEffect(state.value) {
        when (state.value) {
            FirstEntryState.Default -> {}
            FirstEntryState.SignIn -> {
                onSignIn()
            }

            FirstEntryState.SignUp -> {
                onSignUp()
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(WiseCommon.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .paint(
                        painter = painterResource(id = R.drawable.auth_background),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = (prop * 20).dp,
                            end = (prop * 20).dp,
                            top = (prop * 50).dp,
                            bottom = (prop * 60).dp
                        ),
                    painter = painterResource(id = R.drawable.authorization_logo),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = (prop * 16).dp),
                    text = context.getString(R.string.authorization__greeting),
                    style = WiseCommon.typography.appTitle.copy(fontSize = (prop * 24).sp)
                )
                Button(
                    modifier = Modifier
                        .padding(top = (prop * 64).dp, start = (prop * 20).dp, end = (prop * 20).dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = (prop * 12).dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WiseCommon.colors.primary,
                        contentColor = WiseCommon.colors.blackText
                    ),
                    shape = WiseCommon.shape.big,
                    onClick = {
                        firstEntryViewModel.send(FirstEntryEvent.EnterSignUp)
                    }
                ) {
                    Text(
                        text = context.getString(R.string.authorization__sign_up),
                        style = WiseCommon.typography.buttonText.copy(fontSize = (prop * 16).sp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = context.getString(R.string.authorization__question),
                        style = WiseCommon.typography.fadedText.copy(fontSize = (prop * 16).sp)
                    )
                    TextButton(onClick = {
                        firstEntryViewModel.send(FirstEntryEvent.EnterSignIn)
                    }) {
                        Text(
                            text = context.getString(R.string.authorization__sign_in),
                            style = WiseCommon.typography.underlinedText.copy(fontSize = (prop * 16).sp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
