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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class EventoViewModel(
    private val repo: EventoRepository,
    private val loginViewModel: LoginViewModel
) : ViewModel() {

    private val _categoriaSeleccionada = MutableStateFlow("Todos")
    val categoriaSeleccionada: StateFlow<String> = _categoriaSeleccionada.asStateFlow()

    val categorias: StateFlow<List<String>> = repo.categorias
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

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

    fun unirseEvento(eventoId: Int) {
        viewModelScope.launch {
            // Llama a la función corregida en LoginViewModel
            loginViewModel.inscribir(loginViewModel.usuarioActual.value!!.email, eventoId)
            
            // También incrementamos los asistentes del evento
            repo.aumentarAsistentes(eventoId)
        }
    }

    fun seleccionarCategoria(categoria: String) {
        _categoriaSeleccionada.value = categoria
    }
}
