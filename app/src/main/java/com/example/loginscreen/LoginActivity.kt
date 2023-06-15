package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.loginscreen.databinding.ActivityLoginBinding
import com.example.loginscreen.model.BaseResponse
import com.example.loginscreen.model.LoginResponse
import com.example.loginscreen.network.SessionManager
import com.example.loginscreen.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {

                    processLogin(it.data)
                    saveUsername(it.data)
                    stopLoading()
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                    stopLoading()
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            doLogin()


        }

//        binding.btnSignup.setOnClickListener {
//            doSignup()
//        }



    }

    private fun navigateToHome() {
        val intent = Intent(this@LoginActivity, OptionsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    private fun doLogin() {
        val email = binding.edtEmail.text.toString()
        val pwd = binding.edtPassword.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)

    }

//    private fun doSignup() {
//        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
//        finish()
//    }

    private fun showLoading() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressbar.visibility = View.GONE
    }

    private fun processLogin(data: LoginResponse?) {
        showToast("Welcome back " + data?.fname)
        if (!data?.accessToken.isNullOrEmpty()) {
            data?.accessToken.let {
                if (it != null) {
                    SessionManager.saveAuthToken(this, it)
                }
            }
            navigateToHome()
        }
    }

    private fun saveUsername(data: LoginResponse?) {
        if (!data?.username.isNullOrEmpty()) {
            data?.username.let {
                if (it != null) {
                    SessionManager.saveUserName(this, it)
                }
            }
        }
    }


    /*private fun userFname(data: LoginResponse?) {
        if (!data?.fname.isNullOrEmpty()) {
            data?.fname.let {
                if (it != null) {
                    SessionManager.saveFirstName(this, it)
                }
            }

        }
    }
    private fun userLname(data: LoginResponse?) {
        if (!data?.lname.isNullOrEmpty()) {
            data?.lname.let {
                if (it != null) {
                    SessionManager.saveLastName(this, it)
                }
            }

        }
    }*/

    private fun processError(msg: String?) {
        showToast("Email or Password Incorrect $msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}