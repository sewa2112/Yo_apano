package com.example.yo_apano.repository

import com.example.yo_apano.dao.EventoDao
import com.example.yo_apano.model.Evento
import kotlinx.coroutines.flow.Flow


class EventoRepository(private val eventoDao: EventoDao) {
    // Un `Flow` que emite la lista de todos los eventos de la base de datos.
    // La UI puede observar este `Flow` para actualizarse automáticamente cuando los datos cambian.
    val eventos: Flow<List<Evento>> = eventoDao.getEventos()
    val categorias: Flow<List<String>> = eventoDao.getCategorias()


    suspend fun agregarEvento(nombre: String, descripcion: String, direccion: String, categoria: String) {
        // Crea una nueva instancia de `Evento`.
        val nuevo = Evento(
            nombre = nombre,
            descripcion = descripcion,
            direccion = direccion,
            categoria = categoria
        )
        // Inserta el nuevo evento en la base de datos a través del DAO.
        eventoDao.insert(nuevo)
    }


    suspend fun aumentarAsistentes(id: Int) {
        // Obtiene el evento actual de la base de datos.
        val evento = eventoDao.getEventoById(id)
        // Si el evento existe, crea una copia con el número de asistentes actualizado.
        if (evento != null) {
            val eventoActualizado = evento.copy(asistentes = evento.asistentes + 1)
            // Actualiza el evento en la base de datos.
            eventoDao.update(eventoActualizado)
        }
    }



}
