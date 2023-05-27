package com.example.loginscreen.network


import com.example.loginscreen.model.LoginRequest
import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.model.RegRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("user")
    suspend fun getUser(): Response<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("user/signup")
    suspend fun registration(@Body regRequest: RegRequest): Response<LoginResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }

    }
}