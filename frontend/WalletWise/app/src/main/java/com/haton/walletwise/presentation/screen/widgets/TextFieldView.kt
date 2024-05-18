package com.haton.walletwise.presentation.screen.widgets

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.haton.walletwise.presentation.ui.theme.WiseCommon

@Composable
fun TextFieldView(
    placeholder: String,
    value: MutableState<String>,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val prop = (density.density / 2.75)
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = { value.value = it },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = WiseCommon.colors.white,
            unfocusedContainerColor = WiseCommon.colors.white,
            focusedIndicatorColor = WiseCommon.colors.primary,
            unfocusedIndicatorColor = WiseCommon.colors.primary,
        ),
        shape = shape,
        textStyle = WiseCommon.typography.fieldText.copy(fontSize = (prop * 16).sp),
        placeholder = {
            Text(
                text = placeholder,
                style = WiseCommon.typography.fieldText.copy(fontSize = (prop * 16).sp)
            )
        }
    )
}
