package com.example.yo_apano.repository

import com.example.yo_apano.model.Usuario

class UsuarioRepository {

    // Lista simulada de usuarios registrados
    private val usuariosRegistrados = mutableListOf(
        Usuario(email = "test@correo.com", contrasena = "1234"),
        Usuario(email = "sewa@correo.com", contrasena = "scyther")
    )

    fun login(email: String, contrasena: String): Boolean {
        return usuariosRegistrados.any { it.email == email && it.contrasena == contrasena }
    }
}