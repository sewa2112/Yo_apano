package com.example.yo_apano.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yo_apano.model.Evento
import kotlinx.coroutines.flow.Flow

@Dao
interface EventoDao {
    @Query("SELECT * FROM eventos ORDER BY nombre ASC")
    fun getEventos(): Flow<List<Evento>>

    @Query("SELECT * FROM eventos WHERE id = :id")
    fun getEvento(id: Int): Flow<Evento>

    @Query("SELECT * FROM eventos WHERE id = :id")
    suspend fun getEventoById(id: Int): Evento?

    @Insert
    suspend fun insert(evento: Evento)

    @Update
    suspend fun update(evento: Evento)
}
