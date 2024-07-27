package com.ionify.grabbites.ui.foryou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ionify.grabbites.R
import com.ionify.grabbites.adapter.RecommendationAdapter
import com.ionify.grabbites.data.model.FoodListItem
import com.ionify.grabbites.data.model.FoodRecommendation
import com.ionify.grabbites.data.model.Promo
import com.ionify.grabbites.data.model.UserReview
import com.ionify.grabbites.databinding.FragmentForYouBinding
import com.ionify.grabbites.utils.ViewModelFactory

class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private val profileViewModel: ForYouViewModel by viewModels { factory }

    private lateinit var recommendationAdapter: RecommendationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        factory = ViewModelFactory.getInstance(requireActivity())
        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recommendationAdapter = RecommendationAdapter()
        binding.rvRecommendation.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = recommendationAdapter
        }
        val response = generateFoodRecommendationResponse()
        recommendationAdapter.setListFood(response.food, promo = response.promo.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateFoodRecommendationResponse(): FoodRecommendation {
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

        return FoodRecommendation(
            promo = Promo(promoId = "satu", name = "Diskon 125% sampe 2rb"),
            userId = "user123",
            food = foodItems as ArrayList<FoodListItem>
        )
    }

}