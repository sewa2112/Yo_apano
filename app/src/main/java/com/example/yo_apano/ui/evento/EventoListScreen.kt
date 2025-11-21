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
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yo_apano.model.Evento
import com.example.yo_apano.viewmodel.EventoViewModel
import com.example.yo_apano.viewmodel.LoginViewModel


@Composable
fun EventoListScreen(eventoViewModel: EventoViewModel, loginViewModel: LoginViewModel) {
    val eventosFiltrados by eventoViewModel.eventosFiltrados.collectAsState()
    val categorias by eventoViewModel.categorias.collectAsState()
    val categoriaSeleccionada by eventoViewModel.categoriaSeleccionada.collectAsState()
    val usuario by loginViewModel.usuarioActual.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Eventos disponibles", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        val todasLasCategorias = listOf("Todos") + categorias
        ScrollableTabRow(selectedTabIndex = todasLasCategorias.indexOf(categoriaSeleccionada)) {
            todasLasCategorias.forEachIndexed { index, categoria ->
                Tab(
                    selected = index == todasLasCategorias.indexOf(categoriaSeleccionada),
                    onClick = { eventoViewModel.seleccionarCategoria(categoria) },
                    text = { Text(categoria) }
                )
            }
        }

        LazyColumn {
            items(eventosFiltrados) { evento ->
                val yaInscrito = usuario?.eventosInscritos?.contains(evento.id) == true
                EventoCard(
                    evento = evento,
                    onUnirse = { eventoViewModel.unirseEvento(evento.id) },
                    yaInscrito = yaInscrito
                )
            }
        }
    }
}


@Composable
fun EventoCard(evento: Evento, onUnirse: () -> Unit, yaInscrito: Boolean) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(evento.nombre, style = MaterialTheme.typography.titleLarge)
            Text(evento.descripcion)
            Text("Categoría: ${evento.categoria}")
            Text("Asistentes: ${evento.asistentes}")
            Text("Dirección: ${evento.direccion}")
            Spacer(Modifier.height(8.dp))
            Button(onClick = onUnirse, enabled = !yaInscrito) {
                Text(if (yaInscrito) "Ya inscrito" else "Unirse al evento")
            }
        }
    }
}
