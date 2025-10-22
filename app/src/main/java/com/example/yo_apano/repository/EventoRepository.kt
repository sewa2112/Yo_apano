package com.example.yo_apano.repository

import com.example.yo_apano.dao.EventoDao
import com.example.yo_apano.model.Evento
import kotlinx.coroutines.flow.Flow

class EventoRepository(private val eventoDao: EventoDao) {
    val eventos: Flow<List<Evento>> = eventoDao.getEventos()

    suspend fun agregarEvento(nombre: String, descripcion: String, direccion: String) {
        val nuevo = Evento(
            nombre = nombre,
            descripcion = descripcion,
            direccion = direccion
        )
        eventoDao.insert(nuevo)
    }

    suspend fun aumentarAsistentes(id: Int) {
        val evento = eventoDao.getEventoById(id)
        if (evento != null) {
            val eventoActualizado = evento.copy(asistentes = evento.asistentes + 1)
            eventoDao.update(eventoActualizado)
        }
    }

    fun getEvento(id: Int): Flow<Evento> {
        return eventoDao.getEvento(id)
    }
}
