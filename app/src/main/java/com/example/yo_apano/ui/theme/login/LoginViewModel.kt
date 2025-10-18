package com.example.yo_apano.ui.theme.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yo_apano.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usuarioRepository: UserRepository = UserRepository()
) : ViewModel() {

    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _error.value = "Completa todos los campos"
                _loginState.value = false
            } else {
                val success = usuarioRepository.login(email, password)
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