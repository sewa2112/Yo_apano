package com.example.yo_apano.repository

import com.example.yo_apano.model.Usuario


class UsuarioRepository {

    // Una lista mutable que simula una tabla de base de datos para los usuarios.
    // Contiene usuarios predefinidos para propósitos de prueba.
    private val usuariosRegistrados = mutableListOf(
        Usuario(email = "test@correo.com", contrasena = "1234")
    )


    fun login(email: String, contrasena: String): Usuario? {
        // Busca en la lista `usuariosRegistrados` si algún usuario coincide con el email y contraseña proporcionados.
        return usuariosRegistrados.find { it.email == email && it.contrasena == contrasena }
    }

    fun getUsuario(email: String): Usuario? {
        return usuariosRegistrados.find { it.email == email }
    }

    fun registrarUsuario(email: String, contrasena: String): Boolean {
        if (getUsuario(email) != null) {
            return false // El usuario ya existe
        }
        usuariosRegistrados.add(Usuario(email, contrasena))
        return true
    }

    fun inscribirUsuarioAEvento(email: String, eventoId: Int) {
        val usuario = getUsuario(email)
        if (usuario != null && !usuario.eventosInscritos.contains(eventoId)) {
            val usuarioActualizado = usuario.copy(eventosInscritos = usuario.eventosInscritos + eventoId)
            usuariosRegistrados.remove(usuario)
            usuariosRegistrados.add(usuarioActualizado)
        }
    }
}
