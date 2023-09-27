package com.example.interactactivityexcercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.interactactivityexcercise.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActiviyLifeCycle"
    private lateinit var binding : ActivityRegisterBinding

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkBox = binding.checkList
        val registerButton = binding.registerBtn

        with(binding){
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                // Mengaktifkan atau menonaktifkan tombol "Daftar" berdasarkan status checkbox
                registerButton.isEnabled = isChecked
            }

            registerBtn.setOnClickListener {
                if (checkBox.isChecked) {
                    val intentToHomeActivity =
                        Intent(this@RegisterActivity, HomeActivity::class.java)
                    val username = usernameField.text.toString()
                        intentToHomeActivity.putExtra(EXTRA_USERNAME,username)

                    val email = emailField.text.toString()
                        intentToHomeActivity.putExtra(EXTRA_EMAIL, email)

                    val phone = phoneField.text.toString()
                        intentToHomeActivity.putExtra(EXTRA_PHONE,phone)

                    startActivity(intentToHomeActivity)
                } else {
                    // Tampilkan pesan atau lakukan tindakan lain jika checkbox belum dicentang
                    // Misalnya, tampilkan pesan kesalahan
                    Toast.makeText(
                        this@RegisterActivity,
                        "Centang kotak untuk melanjutkan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart dipanggil")
    }

}