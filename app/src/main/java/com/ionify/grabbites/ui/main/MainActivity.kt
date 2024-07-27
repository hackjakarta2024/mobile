package com.ionify.grabbites.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ionify.grabbites.adapter.RecommendationAdapter
import com.ionify.grabbites.data.model.FoodListItem
import com.ionify.grabbites.data.model.FoodRecommendationResponse
import com.ionify.grabbites.data.model.UserReview
import com.ionify.grabbites.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recommendationAdapter: RecommendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        recommendationAdapter = RecommendationAdapter()

        binding.rvRecommendation.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recommendationAdapter
        }

        val response = generateFoodRecommendationResponse()
        recommendationAdapter.setListFood(response.food, promo = response.promo)
    }

    private fun generateFoodRecommendationResponse(): FoodRecommendationResponse {
        val userReviews = arrayListOf(
            UserReview(name = "User A", review = "Enak banget!", rating = 5),
            UserReview(name = "User B", review = "Biasa aja", rating = 3),
            UserReview(name = "User C", review = "Kurang enak", rating = 2)
        )

        val foodItems = (1..10).map { index ->
            FoodListItem(
                ratingTotal = (1..5).random(),
                image = "https://i0.wp.com/resepkoki.id/wp-content/uploads/2016/09/Resep-Nasi-Goreng-Ikan-Teri.jpg?fit=1920%2C1440&ssl=1",
                name = "Food $index",
                id = "food$index",
                realPrice = (50000..100000).random(),
                restaurantName = "Restaurant $index",
                desc = "Deskripsi makanan $index",
                fakePrice = (100000..150000).random(),
                userReview = userReviews as ArrayList<UserReview>
            )
        }

        return FoodRecommendationResponse(
            promo = "Diskon 50%",
            userId = "user123",
            food = foodItems as ArrayList<FoodListItem>
        )
    }

}