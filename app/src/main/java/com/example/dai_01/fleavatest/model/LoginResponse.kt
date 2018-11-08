package com.example.dai_01.fleavatest.model


data class LoginResponse(
    val email: String,
    val id: String,
    val role: String,
    val name: String,
    val is_active: Boolean,
    val token: String
)