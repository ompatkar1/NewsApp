package com.androiddevs.mvvmnewsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androiddevs.mvvmnewsapp.databinding.ActivityLoginBinding
import com.androiddevs.mvvmnewsapp.db.DatabaseHelper


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername,loginPassword)

        }
        binding.signupRedirect.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(username: String, password: String) {
        val userExists = databaseHelper.readUser(username, password)
        if (userExists) {
            Toast.makeText(this, "login Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
        }
    }

}