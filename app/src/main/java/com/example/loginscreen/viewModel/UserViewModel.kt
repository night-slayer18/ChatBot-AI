package com.example.loginscreen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.loginscreen.model.BaseResponse
import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.repo.UserRepository

import kotlinx.coroutines.launch




class UserViewModel (application: Application) : AndroidViewModel(application) {
    private val userRepo = UserRepository()
    val user: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()


    fun getUser() {
        user.value = BaseResponse.Loading()

        viewModelScope.launch {

            try {

                val getuser = getUser()
                val response = userRepo.getUser()
                if (response?.code() == 200) {
                    user.value = BaseResponse.Success(response.body())
                } else {
                    user.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                user.value = BaseResponse.Error(ex.message)
            }

        }

    }
}