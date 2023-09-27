package com.example.interactactivityexcercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interactactivityexcercise.RegisterActivity.Companion.EXTRA_EMAIL
import com.example.interactactivityexcercise.RegisterActivity.Companion.EXTRA_PHONE
import com.example.interactactivityexcercise.RegisterActivity.Companion.EXTRA_USERNAME
import com.example.interactactivityexcercise.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val phone = intent.getStringExtra(EXTRA_PHONE)

        val logout = binding.logoutbtn
        val presensi = binding.presentBtn

        with(binding){
            txtusername.text = "$username"
            txtemail.text = "$email"
            txtphone.text = "$phone"
        }

        presensi.setOnClickListener{
            present()
        }


        logout.setOnClickListener {
            logOut()
        }


    }

    private fun present(){
        val intent = Intent(this@HomeActivity, PresensiActivity::class.java)
        startActivity(intent)
    }
    private fun logOut() {
        val intent = Intent(this@HomeActivity, RegisterActivity::class.java)
        startActivity(intent)
    }
}