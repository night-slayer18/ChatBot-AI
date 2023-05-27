package com.example.loginscreen.repo

import com.example.loginscreen.model.LoginRequest
import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.network.UserApi
import retrofit2.Response

class LoginRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}