package com.example.yo_apano.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.repository.EventoRepository
import kotlinx.coroutines.launch


class EventoViewModel(
    private val repo: EventoRepository = EventoRepository()
) : ViewModel() {

    val eventos = repo.eventos

    fun agregarEvento(nombre: String, descripcion: String, direccion: String) {
        viewModelScope.launch {
            repo.agregarEvento(nombre, descripcion, direccion)
        }
    }

    fun unirseEvento(id: Int) {
        viewModelScope.launch {
            repo.aumentarAsistentes(id)
        }
    }
}