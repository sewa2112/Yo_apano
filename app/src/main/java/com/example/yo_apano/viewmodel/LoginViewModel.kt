package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.model.Usuario
import com.example.yo_apano.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    // El repositorio para gestionar los datos del usuario.
    private val usuarioRepository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    // `_usuarioActual` es un `MutableStateFlow` que emite el estado del usuario actual.
    // Es privado para que solo el ViewModel pueda modificarlo.
    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    // `usuarioActual` es la versión pública e inmutable de `_usuarioActual` que la UI puede observar.
    val usuarioActual = _usuarioActual.asStateFlow()

    // mensaje de error si el inicio de sesión falla.
    private val _error = MutableStateFlow<String?>(null)
    // `error` es la versión pública e inmutable de `_error` para ser observada por la UI.
    val error = _error.asStateFlow()


    fun login(email: String, contrasena: String) {
        viewModelScope.launch {
            // Comprueba si alguno de los campos está vacío.
            if (email.isBlank() || contrasena.isBlank()) {
                _error.value = "Completa todos los campos"
            } else {
                // Llama al método `login` del repositorio para validar las credenciales.
                val usuario = usuarioRepository.login(email, contrasena)
                if (usuario != null) {
                    // Si el inicio de sesión es exitoso, limpia cualquier error y actualiza el estado de inicio de sesión.
                    _error.value = null
                    _usuarioActual.value = usuario
                } else {
                    // Si el inicio de sesión falla, establece un mensaje de error y actualiza el estado de inicio de sesión.
                    _error.value = "Correo o contraseña incorrectos"
                    _usuarioActual.value = null
                }
            }
        }
    }

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
                login(email, contrasena) // Inicia sesión automáticamente
            } else {
                _error.value = "El correo electrónico ya está en uso"
            }
        }
    }

    fun inscribirUsuarioAEvento(eventoId: Int) {
        viewModelScope.launch {
            _usuarioActual.value?.let { usuario ->
                usuarioRepository.inscribirUsuarioAEvento(usuario.email, eventoId)
                // Refresca el estado del usuario para que la UI se actualice
                _usuarioActual.value = usuarioRepository.getUsuario(usuario.email)
            }
        }
    }

    fun limpiarError() {
        _error.value = null
    }
}
