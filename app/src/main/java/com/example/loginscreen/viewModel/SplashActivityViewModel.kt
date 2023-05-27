package com.example.loginscreen.viewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashActivityViewModel : ViewModel() {
    private val _isFinished = MutableLiveData<Boolean>()
    val isFinished: LiveData<Boolean>
        get() = _isFinished
    private val delay = 3000L

    init {
        _isFinished.value = false
        Handler(Looper.getMainLooper()).postDelayed({
            _isFinished.value = true
        }, delay)
    }
}