package com.ionify.grabbites.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.ionify.grabbites.R
import com.ionify.grabbites.adapter.RecommendationAdapter
import com.ionify.grabbites.data.model.FoodListItem
import com.ionify.grabbites.data.model.FoodRecommendation
import com.ionify.grabbites.data.model.Promo
import com.ionify.grabbites.data.model.UserReview
import com.ionify.grabbites.databinding.ActivityMainBinding
import com.ionify.grabbites.ui.foryou.ForYouFragment
import com.ionify.grabbites.ui.login.LoginActivity
import com.ionify.grabbites.ui.splashscreen.SplashScreenViewModel
import com.ionify.grabbites.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factory = ViewModelFactory.getInstance(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = ForYouFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
            binding.searchView.requestFocus()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    search(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.buttonLogout.setOnClickListener {
            mainViewModel.logout()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun search(query: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? ForYouFragment
        fragment?.updateSearchQuery(query)
    }
}
