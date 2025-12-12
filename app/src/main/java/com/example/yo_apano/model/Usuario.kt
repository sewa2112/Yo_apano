package com.example.yo_apano.model

import com.google.gson.annotations.SerializedName

data class Usuario(
    val id: Int,
    val email: String,
    val contrasena: String,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("eventos_inscritos") val eventosInscritos: List<Int>
)
