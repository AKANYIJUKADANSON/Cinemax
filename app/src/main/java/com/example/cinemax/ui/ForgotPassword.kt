package com.example.cinemax.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.cinemax.R
import com.example.cinemax.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)

        binding.buttonSubmit.setOnClickListener {
            val emailToSendLink = binding.edForgotPasswordEmail.text.toString()
            resetPassword(emailToSendLink)
        }

        setUpActionBar()
    }

    // Send email link to set the password
    private fun resetPassword(emailToSendLink:String):Boolean{

        return when {
            TextUtils.isEmpty(emailToSendLink.trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please provide email to send the link", Toast.LENGTH_LONG)
                    .show()
                false
            }
            else -> {
                mFirebaseAuth.sendPasswordResetEmail(emailToSendLink)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            Toast.makeText(
                                this,
                                resources.getString(R.string.emailSentSuccess),
                                Toast.LENGTH_LONG
                            ).show()

                            val intnt = Intent(this, Login::class.java)
                            intnt.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intnt)
                            finish()
                        }
                        else{
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                true
            }
        }

    }

    private fun setUpActionBar(){
        val myToolBar = findViewById<Toolbar>(R.id.forgot_password_toolbar)
        setSupportActionBar(myToolBar)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        myToolBar.setNavigationOnClickListener { onBackPressed() }
    }
}