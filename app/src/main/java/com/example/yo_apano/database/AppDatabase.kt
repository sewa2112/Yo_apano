package com.example.yo_apano.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yo_apano.dao.EventoDao
import com.example.yo_apano.model.Evento


@Database(entities = [Evento::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    abstract fun eventoDao(): EventoDao


    companion object {
        // `@Volatile` asegura que la variable `INSTANCE` sea siempre visible para todos los hilos.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        //Obtiene la instancia Singleton de la base de datos.
        fun getDatabase(context: Context): AppDatabase {
            // Si la instancia ya existe, la devuelve.
            // Si no, crea la base de datos dentro de un bloque sincronizado para evitar condiciones de carrera.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Nombre del archivo de la base de datos
                )
                .fallbackToDestructiveMigration() // Añadido para evitar el bloqueo por migración
                .build()
                INSTANCE = instance
                // Devuelve la nueva instancia.
                instance
            }
        }
    }
}
