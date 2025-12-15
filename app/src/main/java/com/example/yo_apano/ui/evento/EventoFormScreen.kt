package com.example.yo_apano.ui.evento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yo_apano.viewmodel.EventoViewModel


@Composable
fun EventoFormScreen(viewModel: EventoViewModel, onEventoAgregado: () -> Unit) {
    // Estados para almacenar la entrada del usuario para el nombre, descripción y dirección del evento.
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    // `Column` organiza los elementos del formulario verticalmente.
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título del formulario.
        Text("Registrar nuevo evento", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        // Campos de texto para que el usuario introduzca los detalles del evento.
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre del evento") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        // Botón para registrar el evento.
        Button(onClick = {
            // Comprueba que los campos obligatorios no estén vacíos.
            if (nombre.isNotBlank() && direccion.isNotBlank() && categoria.isNotBlank()) {
                // Llama al ViewModel para agregar el evento.
                viewModel.agregarEvento(nombre, descripcion, direccion, categoria)
                // Llama al callback para indicar que el evento fue agregado.
                onEventoAgregado()
            }
        }) {
            Text("Registrar evento")
        }
    }
}
