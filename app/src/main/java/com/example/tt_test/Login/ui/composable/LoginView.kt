package com.example.tt_test.Login.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tt_test.Login.ui.LoginViewModel

@Composable
fun LoginView(viewModel: LoginViewModel) {

    val viewState by viewModel.viewState.collectAsState()
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }
    val dialogText = remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            },
            title = { Text("Login") },
            text = { Text(dialogText.value) }
        )
    }

    when (viewState) {
        is LoginViewModel.ViewState.LoginSuccess -> {
            if ((viewState as LoginViewModel.ViewState.LoginSuccess).isLogged) {
                dialogText.value = "You are logged in"
                showDialog.value = true
            } else {
                dialogText.value = "Error logging in"
                showDialog.value = true
            }
        }

        is LoginViewModel.ViewState.LoginError -> {

        }

        else -> {

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
    ) {
        Text("Username:")
        TextField(
            username.value,
            onValueChange = {
                username.value = it
            },
        )
        Text("Password")
        TextField(
            password.value,
            onValueChange = {
                password.value = it
            },
        )
        Button(
            onClick = {
                viewModel.login(
                    username = username.value,
                    password = password.value
                )
            },
            content = { Text("Login") }
        )
    }
}