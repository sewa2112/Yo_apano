package com.example.yo_apano.repository

import com.example.yo_apano.api.UsuarioApiService
import com.example.yo_apano.model.Usuario

class UsuarioRepository(
    private val apiService: UsuarioApiService
) {

    // LOGIN = simplemente obtener usuario por email y comparar contraseña manualmente
    suspend fun login(email: String, contrasena: String): Usuario? {
        val response = apiService.getUsuario(email)

        if (response.isSuccessful) {
            val user = response.body()
            if (user != null && user.contrasena == contrasena) {
                return user
            }
        }

        return null
    }

    // REGISTRO = POST directo del usuario
    suspend fun registrarUsuario(email: String, contrasena: String): Boolean {
        val nuevoUsuario = Usuario(
            id = 0, // Xano ignora esto
            email = email,
            contrasena = contrasena,
            createdAt = System.currentTimeMillis(),
            eventosInscritos = emptyList()
        )

        val response = apiService.crearUsuario(nuevoUsuario)
        return response.isSuccessful
    }

    // GET usuario por email
    suspend fun getUsuario(email: String): Usuario? {
        val response = apiService.getUsuario(email)
        return if (response.isSuccessful) response.body() else null
    }

    // INSCRIBIR A EVENTO (si decides agregarlo después en Xano)
    suspend fun inscribirUsuarioAEvento(email: String, eventoId: Int): Boolean {
        val usuario = getUsuario(email) ?: return false

        val actualizado = usuario.copy(
            eventosInscritos = usuario.eventosInscritos + eventoId
        )

        val response = apiService.crearUsuario(actualizado)
        return response.isSuccessful
    }
}
