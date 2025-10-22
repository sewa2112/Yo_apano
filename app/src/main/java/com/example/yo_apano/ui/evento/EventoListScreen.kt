package com.example.yo_apano.ui.evento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yo_apano.model.Evento
import com.example.yo_apano.viewmodel.EventoViewModel

@Composable
fun EventoListScreen(viewModel: EventoViewModel) {
    val eventos by viewModel.eventos.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Eventos disponibles", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(eventos) { evento ->
                EventoCard(evento = evento, onUnirse = { viewModel.unirseEvento(evento.id) })
            }
        }
    }
}

@Composable
fun EventoCard(evento: Evento, onUnirse: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(evento.nombre, style = MaterialTheme.typography.titleLarge)
            Text(evento.descripcion)
            Text("Asistentes: ${evento.asistentes}")
            Text("Dirección: ${evento.direccion}")
            Spacer(Modifier.height(8.dp))
            Button(onClick = onUnirse) {
                Text("Unirse al evento")
            }
        }
    }
}
