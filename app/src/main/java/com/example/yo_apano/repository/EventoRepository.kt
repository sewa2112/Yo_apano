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
            Evento(id = nextId++, nombre = "Lanzamiento de nuestra app", descripcion = "¡Celebremos el lanzamiento de nuestra app!", direccion = "Av. Pepito 321, Santiago", asistentes = 10),
            Evento(id = nextId++, nombre = "Colecta solidaria", descripcion = "Juntémonos para ayudar a los necesitados", direccion = "Calle Falsa 123, Santiago", asistentes = 5),
            Evento(id = nextId++, nombre = "Olla común", descripcion = "Oya común para compartir", direccion = "Parque Metropolitano de Santiago", asistentes = 25)
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
