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
import com.example.yo_apano.ui.evento.EventoFormScreen
import com.example.yo_apano.ui.evento.EventoListScreen
import com.example.yo_apano.ui.login.LoginScreen
import com.example.yo_apano.ui.login.RegistroScreen
import com.example.yo_apano.viewmodel.LoginViewModel
import com.example.yo_apano.viewmodel.EventoViewModel
import com.example.yo_apano.viewmodel.ViewModelFactory


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // `setContent` define el diseño de la actividad con elementos Composable.
        setContent {
            val application = (application as YoApanoApplication)
            val loginViewModel: LoginViewModel by viewModels { ViewModelFactory(usuarioRepository = application.usuarioRepository) }
            val eventoViewModel: EventoViewModel by viewModels { ViewModelFactory(eventoRepository = application.eventoRepository, loginViewModel = loginViewModel) }

            val usuarioActual by loginViewModel.usuarioActual.collectAsState()
            var showRegistroScreen by remember { mutableStateOf(false) }

            // La UI cambia dependiendo de si el usuario ha iniciado sesión o no.
            if (usuarioActual != null) {
                // `showForm` es un estado para mostrar/ocultar el formulario de creación de eventos.
                var showForm by remember { mutableStateOf(false) }

                // Si `showForm` es verdadero, se muestra el formulario de registro de eventos.
                if (showForm) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Registrar Evento",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        // `EventoFormScreen` es el Composable para el formulario de eventos.
                        // Al completar, se oculta el formulario.
                        EventoFormScreen(eventoViewModel) {
                            showForm = false
                        }
                    }
                } else {
                    // Si `showForm` es falso, se muestra la lista de eventos.
                    Column {
                        // Botón para mostrar el formulario de registro.
                        Button(onClick = { showForm = true }) {
                            Text("Agregar Evento")
                        }
                        // `EventoListScreen` es el Composable que muestra la lista de eventos.
                        EventoListScreen(eventoViewModel, loginViewModel)
                    }
                }
            } else {
                // Si el usuario no ha iniciado sesión, se muestra la pantalla de inicio de sesión o de registro.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF003366) // Color de fondo azul oscuro
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
                        if (showRegistroScreen) {
                            RegistroScreen(viewModel = loginViewModel, onRegistroExitoso = {
                                showRegistroScreen = false
                                loginViewModel.limpiarError()
                            })
                        } else {
                            LoginScreen(viewModel = loginViewModel, onNavigateToRegistro = {
                                showRegistroScreen = true
                                loginViewModel.limpiarError()
                            })
                        }
                    }
                }
            }
        }
    }
}
