package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun login(email: String, contrasena: String) {
        viewModelScope.launch {
            if (email.isBlank() || contrasena.isBlank()) {
                _error.value = "Completa todos los campos"
                _loginState.value = false
            } else {
                val success = usuarioRepository.login(email, contrasena)
                if (success) {
                    _error.value = null
                    _loginState.value = true
                } else {
                    _error.value = "Correo o contraseña incorrectos"
                    _loginState.value = false
                }
            }
        }
    }
}