package com.example.yo_apano.repository

import com.example.yo_apano.model.Evento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventoRepository {
    private val _eventos = MutableStateFlow<List<Evento>>(emptyList())
    val eventos = _eventos.asStateFlow()

    private var nextId = 1

    init {
        _eventos.value = listOf(
            Evento(id = nextId++, nombre = "Fiesta de lanzamiento", descripcion = "¡Celebremos el lanzamiento de nuestra app!", direccion = "Av. Siempreviva 742, Springfield", asistentes = 10),
            Evento(id = nextId++, nombre = "Reunión de desarrolladores", descripcion = "Juntémonos a codear y compartir ideas.", direccion = "Calle Falsa 123, Santiago", asistentes = 5),
            Evento(id = nextId++, nombre = "Picnic en el parque", descripcion = "Un día de relajo y buena comida en el Parque Metropolitano.", direccion = "Parque Metropolitano de Santiago", asistentes = 25)
        )
    }

    fun agregarEvento(nombre: String, descripcion: String, direccion: String) {
        val nuevo = Evento(
            id = nextId++,
            nombre = nombre,
            descripcion = descripcion,
            direccion = direccion
        )
        _eventos.value = _eventos.value + nuevo
    }

    fun aumentarAsistentes(id: Int) {
        _eventos.value = _eventos.value.map {
            if (it.id == id) it.copy(asistentes = it.asistentes + 1) else it
        }
    }
}
