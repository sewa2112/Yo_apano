package com.example.yo_apano.model


data class Usuario(
    val email: String,
    val contrasena: String,
    val eventosInscritos: List<Int> = emptyList()
)

