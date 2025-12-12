package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.model.Usuario
import com.example.yo_apano.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    // -------- LOGIN --------
    fun login(email: String, contrasena: String) {
        viewModelScope.launch {
            if (email.isBlank() || contrasena.isBlank()) {
                _error.value = "Completa todos los campos"
                return@launch
            }

            try {
                val usuario = usuarioRepository.login(email, contrasena)
                if (usuario != null) {
                    _error.value = null
                    _usuarioActual.value = usuario
                } else {
                    _error.value = "Correo o contraseña incorrectos"
                    _usuarioActual.value = null
                }
            } catch (e: Exception) {
                _error.value = "Error de red: No se pudo conectar al servidor"
                _usuarioActual.value = null
            }
        }
    }

    // -------- REGISTRO --------
    fun registrarUsuario(email: String, contrasena: String, confirmarContrasena: String) {
        viewModelScope.launch {
            if (email.isBlank() || contrasena.isBlank() || confirmarContrasena.isBlank()) {
                _error.value = "Todos los campos son obligatorios"
                return@launch
            }
            if (contrasena != confirmarContrasena) {
                _error.value = "Las contraseñas no coinciden"
                return@launch
            }

            val exito = usuarioRepository.registrarUsuario(email, contrasena)
            if (exito) {
                _error.value = null
                login(email, contrasena)
            } else {
                _error.value = "El correo electrónico ya está en uso"
            }
        }
    }

    // -------- INSCRIPCIÓN --------
    fun inscribir(email: String, eventoId: Int) {
        viewModelScope.launch {
            val ok = usuarioRepository.inscribirUsuarioAEvento(email, eventoId)

            if (ok) {
                _error.value = null
                val usuarioActualizado = usuarioRepository.getUsuario(email)
                _usuarioActual.value = usuarioActualizado
            } else {
                _error.value = "No se pudo inscribir al evento"
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }
}
