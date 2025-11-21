package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.model.Evento
import com.example.yo_apano.repository.EventoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class EventoViewModel(
    private val repo: EventoRepository,
    private val loginViewModel: LoginViewModel
) : ViewModel() {

    // StateFlow for the selected category
    private val _categoriaSeleccionada = MutableStateFlow("Todos")
    val categoriaSeleccionada: StateFlow<String> = _categoriaSeleccionada.asStateFlow()

    // StateFlow for the list of all available categories
    val categorias: StateFlow<List<String>> = repo.categorias
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // `eventosFiltrados` combines the full list of events with the selected category
    val eventosFiltrados: StateFlow<List<Evento>> = combine(
        repo.eventos,
        _categoriaSeleccionada
    ) { eventos, categoria ->
        if (categoria == "Todos") {
            eventos
        } else {
            eventos.filter { it.categoria.equals(categoria, ignoreCase = true) }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun agregarEvento(nombre: String, descripcion: String, direccion: String, categoria: String) {
        viewModelScope.launch {
            repo.agregarEvento(nombre, descripcion, direccion, categoria)
        }
    }

    fun unirseEvento(id: Int) {
        viewModelScope.launch {
            val usuario = loginViewModel.usuarioActual.firstOrNull()
            if (usuario != null && !usuario.eventosInscritos.contains(id)) {
                // Aumentar asistentes es algo que debería hacer el backend
                // Por ahora, lo dejamos comentado o lo eliminamos.
                // repo.aumentarAsistentes(id)
                loginViewModel.inscribirUsuarioAEvento(id)
            }
        }
    }

    fun seleccionarCategoria(categoria: String) {
        _categoriaSeleccionada.value = categoria
    }
}
