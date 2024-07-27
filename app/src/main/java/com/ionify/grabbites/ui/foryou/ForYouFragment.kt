package com.ionify.grabbites.ui.foryou

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ionify.grabbites.adapter.RecommendationAdapter
import com.ionify.grabbites.data.model.FoodListItem
import com.ionify.grabbites.data.model.FoodRecommendation
import com.ionify.grabbites.data.model.Promo
import com.ionify.grabbites.data.model.UserReview
import com.ionify.grabbites.databinding.FragmentForYouBinding
import com.ionify.grabbites.utils.Result
import com.ionify.grabbites.utils.ViewModelFactory

class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private val forYouViewModel: ForYouViewModel by viewModels { factory }

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
        forYouViewModel.fypData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    val foods = result.data.food
                    val promo = result.data.promo.name
                    recommendationAdapter.setListFood(foods, promo)
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(requireActivity(), "Gagal mendapatkan data rekomendasi makanan.", Toast.LENGTH_LONG).show()
                }
            }
        })
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

    fun updateSearchQuery(query: String) {
        Toast.makeText(requireActivity(), query, Toast.LENGTH_SHORT).show()
        val response = generateFoodRecommendationResponse()
        recommendationAdapter.setListFood(response.food, promo = response.promo.name)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.apply {
                progressBar.visibility = View.VISIBLE
                rvRecommendation.visibility = View.GONE
            }
        } else {
            binding.apply {
                progressBar.visibility = View.GONE
                rvRecommendation.visibility = View.VISIBLE
            }
        }
    }
}