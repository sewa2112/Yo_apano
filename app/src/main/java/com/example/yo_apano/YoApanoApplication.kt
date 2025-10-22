package com.example.yo_apano

import android.app.Application
import com.example.yo_apano.database.AppDatabase
import com.example.yo_apano.repository.EventoRepository

class YoApanoApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { EventoRepository(database.eventoDao()) }
}
