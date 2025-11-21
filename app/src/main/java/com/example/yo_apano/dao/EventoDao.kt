package com.example.yo_apano.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yo_apano.model.Evento
import kotlinx.coroutines.flow.Flow

/**
 * `EventoDao` es una interfaz de Objeto de Acceso a Datos (DAO) para la tabla `eventos`.
 * Define los métodos para interactuar con la base de datos de eventos utilizando Room.
 */
@Dao
interface EventoDao {
    //busca todos los eventos.
    @Query("SELECT * FROM eventos ORDER BY nombre ASC")
    fun getEventos(): Flow<List<Evento>>

    //busca un evento.
    @Query("SELECT * FROM eventos WHERE id = :id")
    fun getEvento(id: Int): Flow<Evento>

    //busca un evento por el id.
    @Query("SELECT * FROM eventos WHERE id = :id")
    suspend fun getEventoById(id: Int): Evento?

    //Obtiene todas las categorías únicas
    @Query("SELECT DISTINCT categoria FROM eventos ORDER BY categoria ASC")
    fun getCategorias(): Flow<List<String>>

    //Inserta un nuevo evento en la base de datos.
    @Insert
    suspend fun insert(evento: Evento)


     //Actualiza un evento existente en la base de datos.

    @Update
    suspend fun update(evento: Evento)
}
