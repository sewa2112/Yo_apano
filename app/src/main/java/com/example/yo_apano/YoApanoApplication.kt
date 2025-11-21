package com.example.yo_apano

import android.app.Application
import com.example.yo_apano.database.AppDatabase
import com.example.yo_apano.repository.EventoRepository
import com.example.yo_apano.repository.UsuarioRepository


class YoApanoApplication : Application() {
    // `database` es una instancia de la base de datos de la aplicación (AppDatabase).
    // Se inicializa de forma perezosa (`lazy`) para que se cree solo cuando se accede por primera vez.
    val database by lazy { AppDatabase.getDatabase(this) }

    // `repository` es una instancia del repositorio de eventos (`EventoRepository`).
    // También se inicializa de forma perezosa y depende de la instancia de la base de datos.
    val eventoRepository by lazy { EventoRepository(database.eventoDao()) }
    val usuarioRepository by lazy { UsuarioRepository() }
}
