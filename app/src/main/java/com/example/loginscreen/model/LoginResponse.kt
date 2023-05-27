package com.example.loginscreen.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject




data class LoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("is_error")
    val isError: Boolean?,
    @SerializedName("token")
    val accessToken: String?,
    @SerializedName("first_name")
    val fname: String?,
    @SerializedName("last_name")
    val lname: String,
    @SerializedName("email")
    val email: String?,
    @SerializedName("user_name")
    val username: String
){


/*
    val response = LoginResponse(fname = this.fname,lname = this.lname, message = this.message, isError = this.isError, email = this.email, accessToken = this.accessToken)

    val jsonString = Gson().toJson(LoginResponse(this.message,this.isError,this.accessToken,this.fname,this.lname,this.email))*/


   /* override fun toString(): String {
        *//*return "Category [title: ${this.title}, author: ${this.author}, categories: ${this.categories}]"*//*
        return "{\"fname\": \"${this.fname}\", \"lname\": \"${this.lname}\"}"}*/
/* lateinit var loginResponse: LoginResponse
    var reader: JSONObject = JSONObject()


    val jsonString = Gson().toJson(loginResponse)*/
        /*@JvmName("getName1")
    public fun getName(): String? {
        return name
    }*/
    }