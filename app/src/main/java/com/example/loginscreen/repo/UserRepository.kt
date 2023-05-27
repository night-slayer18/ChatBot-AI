package com.example.loginscreen.repo

import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.network.UserApi
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Response

class UserRepository {

    suspend fun getUser(): Response<LoginResponse>? {
        return  UserApi.getApi()?.getUser()
    }
}