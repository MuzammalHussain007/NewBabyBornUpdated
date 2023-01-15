package com.example.newbabyborn.fragment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()

    }

    private fun innit() {
        binding.loginToolbar.backbtn.visibility = View.GONE
        binding.loginToolbar.btnTxt.visibility = View.GONE
        binding.btnLogin.setOnClickListener {
            binding.progressbar.visibility = View.GONE
            authentication()
        }
        binding.signupBTN.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun authentication() {

        if (binding.etEmailLogin.text.isEmpty()) {
            binding.etEmailLogin.error = getString(R.string.emailisnotempty)
            binding.etEmailLogin.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmailLogin.text).matches()) {
            binding.etEmailLogin.error = getString(R.string.emailaddressnotvalid)
            binding.etEmailLogin.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }

        if (binding.etPasswordLogin.text.isEmpty()) {
            binding.etPasswordLogin.error = getString(R.string.passwordisnotempty)
            binding.etPasswordLogin.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()
        login(email,password)

    }

    private fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("abce______", "signInWithEmail:success")
                    val user = FirebaseAuth.getInstance().currentUser
                    Log.d("abce______", "signInWithEmail:success"+user)
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("abce______", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}