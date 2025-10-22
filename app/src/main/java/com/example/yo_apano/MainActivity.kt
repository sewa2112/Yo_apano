package com.example.yo_apano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yo_apano.ui.evento.EventoFormScreen
import com.example.yo_apano.ui.evento.EventoListScreen
import com.example.yo_apano.ui.login.LoginScreen
import com.example.yo_apano.viewmodel.LoginViewModel
import com.example.yo_apano.viewmodel.EventoViewModel
import com.example.yo_apano.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    private val eventoViewModel: EventoViewModel by viewModels {
        ViewModelFactory((application as YoApanoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel: LoginViewModel = viewModel()
            val isLoggedIn by loginViewModel.loginState.collectAsState()

            if (isLoggedIn) {
                var showForm by remember { mutableStateOf(false) }

                if (showForm) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Registrar Evento",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        EventoFormScreen(eventoViewModel) {
                            // Cuando se agrega, vuelve a la lista
                            showForm = false
                        }
                    }
                } else {
                    Column {
                        Button(onClick = { showForm = true }) {
                            Text("Agregar Evento")
                        }
                        EventoListScreen(eventoViewModel)
                    }
                }
            } else {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF003366) // Un azul oscuro
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Yo Apaño",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
                        )
                        LoginScreen(loginViewModel)
                    }
                }
            }
        }
    }
}
