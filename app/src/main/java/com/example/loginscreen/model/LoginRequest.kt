package com.example.loginscreen.model

import com.google.gson.annotations.SerializedName


data class LoginRequest(

    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val Password: String
)