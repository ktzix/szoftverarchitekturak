package com.example.fastfashion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fastfashion.model.IdResult
import com.example.fastfashion.model.User
import com.example.fastfashion.model.UserCreate
import com.example.fastfashion.network.AuthInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val authInteractor=AuthInteractor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            val user= UserCreate(input_username.text.toString(),input_password.text.toString())
            authInteractor.login(user, this::onLoginSuccess, this::onLoginError)
        }
        btn_register.setOnClickListener {
            val user= UserCreate(input_username.text.toString(),input_password.text.toString())
            authInteractor.register(user, this::onRegisterSuccess, this::onRegisterError)
        }

    }

    private fun onLoginSuccess(idres: IdResult){
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("id", idres.userId)
        startActivity(intent)
    }

    private fun onLoginError(e: Throwable) {
        e.printStackTrace()
        Toast.makeText(applicationContext, "Sikertelen bejelentkezés!", Toast.LENGTH_LONG).show()


    }

    private fun onRegisterSuccess(idres: IdResult){
        Toast.makeText(applicationContext, "Sikeres regisztráció!", Toast.LENGTH_LONG).show()
    }

    private fun onRegisterError(e: Throwable){
        Toast.makeText(applicationContext, "Sikertelen regisztráció!", Toast.LENGTH_LONG).show()
        e.printStackTrace()
    }
}
