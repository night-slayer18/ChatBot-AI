package com.example.loginscreen.model

import com.google.gson.annotations.SerializedName

data class RegRequest(
    @SerializedName("user_name")
    val username: String,
    @SerializedName("Password")
    val Password: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("user_type")
    val user_type: String
)