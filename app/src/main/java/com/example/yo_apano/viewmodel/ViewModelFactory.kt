package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yo_apano.repository.EventoRepository
import com.example.yo_apano.repository.UsuarioRepository


class ViewModelFactory(
    private val eventoRepository: EventoRepository? = null,
    private val usuarioRepository: UsuarioRepository? = null,
    private val loginViewModel: LoginViewModel? = null
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventoViewModel::class.java)) {
            require(eventoRepository != null && loginViewModel != null) { "EventoRepository and LoginViewModel must not be null" }
            @Suppress("UNCHECKED_CAST")
            return EventoViewModel(eventoRepository, loginViewModel) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            require(usuarioRepository != null) { "UsuarioRepository must not be null" }
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(usuarioRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
