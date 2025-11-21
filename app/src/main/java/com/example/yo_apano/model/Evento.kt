package com.example.yo_apano.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "eventos")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val categoria: String,
    val asistentes: Int = 0
)
