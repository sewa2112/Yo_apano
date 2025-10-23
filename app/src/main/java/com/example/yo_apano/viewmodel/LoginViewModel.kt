package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    // El repositorio para gestionar los datos del usuario.
    private val usuarioRepository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    // `_loginState` es un `MutableStateFlow` que emite el estado de inicio de sesión actual (logueado o no).
    // Es privado para que solo el ViewModel pueda modificarlo.
    private val _loginState = MutableStateFlow(false)
    // `loginState` es la versión pública e inmutable de `_loginState` que la UI puede observar.
    val loginState = _loginState.asStateFlow()

    // mensaje de error si el inicio de sesión falla.
    private val _error = MutableStateFlow<String?>(null)
    // `error` es la versión pública e inmutable de `_error` para ser observada por la UI.
    val error = _error.asStateFlow()


    fun login(email: String, contrasena: String) {
        viewModelScope.launch {
            // Comprueba si alguno de los campos está vacío.
            if (email.isBlank() || contrasena.isBlank()) {
                _error.value = "Completa todos los campos"
                _loginState.value = false
            } else {
                // Llama al método `login` del repositorio para validar las credenciales.
                val success = usuarioRepository.login(email, contrasena)
                if (success) {
                    // Si el inicio de sesión es exitoso, limpia cualquier error y actualiza el estado de inicio de sesión.
                    _error.value = null
                    _loginState.value = true
                } else {
                    // Si el inicio de sesión falla, establece un mensaje de error y actualiza el estado de inicio de sesión.
                    _error.value = "Correo o contraseña incorrectos"
                    _loginState.value = false
                }
            }
        }
    }
}
