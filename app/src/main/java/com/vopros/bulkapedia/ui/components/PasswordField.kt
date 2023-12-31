package com.vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
    state: MutableState<String>,
    label: Int,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        modifier = modifier,
        label = { Text(label) },
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BulkaPediaTheme.colors.white,
            cursorColor = BulkaPediaTheme.colors.tintColor,
            unfocusedBorderColor = BulkaPediaTheme.colors.secondary,
            focusedBorderColor = BulkaPediaTheme.colors.tintColor
        )
    )
}

@Composable
fun PasswordField(
    state: MutableState<String>,
    label: Int
) {
    var isVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        trailingIcon = {
            IconToggleButton(checked = isVisible, onCheckedChange = { isVisible = it }) {
                Icon(
                    if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = null,
                    tint = BulkaPediaTheme.colors.tintColor
                )
            }
        },
        label = { Text(label) },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BulkaPediaTheme.colors.white,
            cursorColor = BulkaPediaTheme.colors.tintColor,
            unfocusedBorderColor = BulkaPediaTheme.colors.secondary,
            focusedBorderColor = BulkaPediaTheme.colors.tintColor
        )
    )
}