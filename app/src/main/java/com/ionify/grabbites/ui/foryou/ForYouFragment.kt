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
        recommendationAdapter = RecommendationAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    fun updateSearchQuery(query: String) {
        forYouViewModel.searchFoodData(query).observe(viewLifecycleOwner, Observer { result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        val foods = result.data.food
                        recommendationAdapter.setListFood(foods, promo = "")
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(requireActivity(), "Gagal mendapatkan data makanan yang anda inginkan.", Toast.LENGTH_LONG).show()
                    }
                }
            }

        })
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