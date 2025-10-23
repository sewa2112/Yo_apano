package com.example.yo_apano.repository

import com.example.yo_apano.model.Usuario


class UsuarioRepository {

    // Una lista mutable que simula una tabla de base de datos para los usuarios.
    // Contiene usuarios predefinidos para propósitos de prueba.
    private val usuariosRegistrados = mutableListOf(
        Usuario(email = "test@correo.com", contrasena = "1234"),
        Usuario(email = "sewa@correo.com", contrasena = "scyther")
    )


    fun login(email: String, contrasena: String): Boolean {
        // Busca en la lista `usuariosRegistrados` si algún usuario coincide con el email y contraseña proporcionados.
        return usuariosRegistrados.any { it.email == email && it.contrasena == contrasena }
    }
}
