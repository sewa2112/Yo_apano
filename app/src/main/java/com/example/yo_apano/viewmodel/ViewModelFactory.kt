package com.example.yo_apano.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.yo_apano.repository.EventoRepository


class ViewModelFactory(private val repository: EventoRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Comprueba si la clase solicitada es `EventoViewModel`.
        if (modelClass.isAssignableFrom(EventoViewModel::class.java)) {
            // Si es así, crea una instancia de `EventoViewModel` y le pasa el repositorio.
            // `@Suppress("UNCHECKED_CAST")` se usa para suprimir la advertencia de conversión insegura.
            @Suppress("UNCHECKED_CAST")
            return EventoViewModel(repository) as T
        }
        // Si la clase no es la que se esperaba, lanza una excepción.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
