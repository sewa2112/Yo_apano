package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.repository.EventoRepository
import kotlinx.coroutines.launch


class EventoViewModel(
    private val repo: EventoRepository
) : ViewModel() {

    // `eventos` es un `Flow` que emite la lista actual de eventos desde el repositorio.
    // La UI puede recoger este `Flow` para mostrar los eventos actualizados.
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
