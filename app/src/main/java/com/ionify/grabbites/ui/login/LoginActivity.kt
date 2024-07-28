package com.ionify.grabbites.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import com.ionify.grabbites.R
import com.ionify.grabbites.databinding.ActivityLoginBinding
import com.ionify.grabbites.ui.main.MainActivity
import com.ionify.grabbites.utils.AlertDialogHelper
import com.ionify.grabbites.utils.Result
import com.ionify.grabbites.utils.ViewModelFactory
import kotlinx.coroutines.launch

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
        setupAction()
    }

    private fun setupUi() {
        loginViewModel.loginDescText.observe(this@LoginActivity) { value ->
            binding.tvLoginDesc.text = HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showFailedDialog(
                    getString(R.string.failed),
                    getString(R.string.required_form),
                    getString(R.string.next)
                )
            } else {
                loginViewModel.postLogin(email, password).observe(this@LoginActivity) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                showLoading(true)
                                Log.d("Login result", "Loading...")
                            }
                            is Result.Success -> {
                                showLoading(false)
                                Log.d("Login result", "Success")
                                Toast.makeText(applicationContext, result.data, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                                finish()
                            }

                            is Result.Error -> {
                                showLoading(false)
                                Log.e("Loading result", "Error")
                                val errorMessage =
                                    if (result.error != "") result.error else "Failed to login"
                                showFailedDialog(
                                    getString(R.string.failed),
                                    errorMessage,
                                    getString(R.string.next)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showFailedDialog(title: String, message: String, negativeButtonLabel: String) {
        AlertDialogHelper.showAlertDialogNegative(
            this@LoginActivity,
            title,
            message,
            negativeButtonLabel
        )
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.btnLogin.text = getString(R.string.loading)
        } else {
            binding.btnLogin.text = getString(R.string.login)
        }
    }
}