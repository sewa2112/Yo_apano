package com.example.yo_apano.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.yo_apano.viewmodel.LoginViewModel


@Composable
fun LoginScreen(viewModel: LoginViewModel, onNavigateToRegistro: () -> Unit) {
    // `email` y `password` son estados que almacenan la entrada del usuario para los campos de texto.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // `error` observa el estado de error del ViewModel y muestra un mensaje si el inicio de sesión falla.
    val error by viewModel.error.collectAsState()

    // `Column` organiza sus elementos secundarios verticalmente.
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de texto para que el usuario introduzca su correo electrónico.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
        // `Spacer` añade un espacio vertical entre los campos de texto.
        Spacer(modifier = Modifier.height(8.dp))
        // Campo de texto para que el usuario introduzca su contraseña.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
        // Muestra un mensaje de error si el valor de `error` no es nulo.
        error?.let {
            Text(it, color = Color.White)
        }
        // `Spacer` añade espacio antes del botón de inicio de sesión.
        Spacer(modifier = Modifier.height(16.dp))
        // Botón que, al hacer clic, invoca la función `login` del ViewModel.
        Button(
            onClick = { viewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToRegistro) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}
