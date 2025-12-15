package com.example.yo_apano.api

import com.example.yo_apano.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioApiService {
    @GET("usuario")
    suspend fun getUsuario(
        @Query("email") email: String
    ): Response<Usuario>

    @POST("usuario")
    suspend fun crearUsuario(
        @Body usuario: Usuario
    ): Response<Usuario>
}
