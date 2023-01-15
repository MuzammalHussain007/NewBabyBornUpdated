package com.example.newbabyborn.fragment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivitySignUpBinding
import com.example.newbabyborn.modal.User
import com.example.newbabyborn.util.AppStorage
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()
    }

    private fun innit() {
        binding.signupActivity.backbtn.setOnClickListener {
            onBackPressed()
        }
        binding.signupActivity.btnTxt.text = "SignUp"
        AppStorage.init(this)
        binding.signUpbtn.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            validateAndSignUp()
        }

    }

    private fun validateAndSignUp() {

        if (binding.etFullName.text.isEmpty()) {
            binding.etFullName.error = getString(R.string.TitleFieldmustnotempty)
            binding.etFullName.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }

        if (binding.etEmail.text.isEmpty()) {
            binding.etEmail.error = getString(R.string.emailisnotempty)
            binding.etEmail.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()) {
            binding.etEmail.error = getString(R.string.emailaddressnotvalid)
            binding.etEmail.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }

        if (binding.etPassword.text.isEmpty()) {
            binding.etPassword.error = getString(R.string.passwordisnotempty)
            binding.etPassword.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }

        if (binding.etConfirmpassword.text.isEmpty()) {
            binding.etConfirmpassword.error = getString(R.string.cnfrmpasswordpasswordisnotempty)
            binding.etConfirmpassword.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }

        if (binding.etPassword.text.toString() != binding.etConfirmpassword.text.toString()) {
            binding.etPassword.error = getString(R.string.passwordnotmatch)
            binding.etConfirmpassword.error = getString(R.string.passwordnotmatch)
            binding.etPassword.requestFocus()
            binding.progressbar.visibility = View.GONE
            return
        }

        val name = binding.etFullName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        startAuthentication(name, email, password)


    }

    private fun startAuthentication(fullname: String, email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    AppStorage.setUser(User(fullname,email,password))
                    binding.progressbar.visibility = View.GONE
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Some Thing went Wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }

}