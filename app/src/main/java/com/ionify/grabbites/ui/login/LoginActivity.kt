package com.ionify.grabbites.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.ionify.grabbites.databinding.ActivityLoginBinding
import com.ionify.grabbites.ui.main.MainActivity
import com.ionify.grabbites.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        factory = ViewModelFactory.getInstance(this)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {
        loginViewModel.loginDescText.observe(this@LoginActivity) { value ->
            binding.tvLoginDesc.text = HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}