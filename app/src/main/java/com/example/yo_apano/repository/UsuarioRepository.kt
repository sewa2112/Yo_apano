package com.example.yo_apano.repository

import com.example.yo_apano.model.Usuario

class UsuarioRepository {

    // Lista simulada de usuarios registrados
    private val usuariosRegistrados = listOf(
        Usuario("test@correo.com", "1234"),
        Usuario("sewa@correo.com", "scyther")
    )

    fun login(email: String, contrasena: String): Boolean {
        return usuariosRegistrados.any { it.email == email && it.contrasena == contrasena }
    }
}