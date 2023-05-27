package com.example.loginscreen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginscreen.model.BaseResponse
import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.model.RegRequest
import com.example.loginscreen.repo.RegRepository
import kotlinx.coroutines.launch

class RegViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo = RegRepository()
    val regResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun regUser(pwd: String,email: String,fname: String,lname:String,phone: String,userType: String ,username: String) {

        regResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val regRequest = RegRequest(
                    Password = pwd,
                    email = email,
                    first_name = fname,
                    last_name = lname,
                    phone = phone,
                    user_type = userType,
                    username = username
                )
                val response = userRepo.regUser(regRequest = regRequest)
                if (response?.code() == 200) {
                    regResult.value = BaseResponse.Success(response.body())
                } else {
                    regResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                regResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}