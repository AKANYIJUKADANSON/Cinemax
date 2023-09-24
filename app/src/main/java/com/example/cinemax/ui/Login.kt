package com.example.cinemax.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cinemax.MainActivity
import com.example.cinemax.R
import com.example.cinemax.databinding.ActivityLoginBinding
import com.example.cinemax.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Login button
        binding.buttonLogin.setOnClickListener {
            if (validateUserLoginDetails()){
                val userEmail = binding.edLoginEmail.text.toString()
                val userPassword = binding.edLoginPassword.text.toString()
                login(userEmail, userPassword)
            }
        }

        // Forgot password
        binding.tvForgotPassword.setOnClickListener {
            val intent = Intent(this@Login, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }

        // Have no account
        binding.llHaveNoAccount.setOnClickListener {
            val intent = Intent(this@Login, Signup::class.java)
            startActivity(intent)
            finish()
        }

        // making the progress dialog invisible
        binding.postProgressBar.visibility = View.INVISIBLE
    }

    // Validating User data
    private fun validateUserLoginDetails():Boolean {
        val loginEmail = binding.edLoginEmail.text.toString()
        val loginPassword = binding.edLoginPassword.text.toString()

        return when {
            TextUtils.isEmpty(loginEmail.trim { it <= ' ' }) ->{
                Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(loginPassword.trim { it <= ' ' }) ->{
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_LONG).show()
                false
            }

            else -> {
                return true
            }
        }

    }

    // Login function
    private fun login(userEmail:String, userPassword:String){
        binding.postProgressBar.visibility = View.INVISIBLE
        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnSuccessListener {
                binding.postProgressBar.visibility = View.GONE
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()

                Toast.makeText(this, "You have been logged in successfully", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                binding.postProgressBar.visibility = View.GONE
                Toast.makeText(this, "Invalid email or password, Please try again", Toast.LENGTH_LONG).show()
            }
    }

}