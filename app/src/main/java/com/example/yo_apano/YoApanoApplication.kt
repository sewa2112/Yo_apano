package com.example.yo_apano

import android.app.Application
import com.example.yo_apano.api.RetrofitClient
import com.example.yo_apano.database.AppDatabase
import com.example.yo_apano.repository.EventoRepository
import com.example.yo_apano.repository.UsuarioRepository


class YoApanoApplication : Application() {
    // `database` es una instancia de la base de datos de la aplicación (AppDatabase).
    // Se inicializa de forma perezosa (`lazy`) para que se cree solo cuando se accede por primera vez.
    val database by lazy { AppDatabase.getDatabase(this) }

    // Instancia del servicio de la API de Retrofit.
    private val usuarioApiService by lazy { RetrofitClient.instance }

    // `repository` es una instancia del repositorio de eventos (`EventoRepository`).
    val eventoRepository by lazy { EventoRepository(database.eventoDao()) }

    // El `UsuarioRepository` ahora recibe la instancia del servicio de la API.
    val usuarioRepository by lazy { UsuarioRepository(usuarioApiService) }
}
