package com.example.yo_apano.model

data class Evento(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val asistentes: Int = 0
)
