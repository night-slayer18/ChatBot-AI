package com.example.loginscreen.repo


import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.model.RegRequest
import com.example.loginscreen.network.UserApi
import retrofit2.Response

class RegRepository {

    suspend fun regUser(regRequest: RegRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.registration(regRequest=regRequest)
    }
}