package com.ionify.grabbites.adapter

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ionify.grabbites.R
import com.ionify.grabbites.data.model.FoodListItem
import com.ionify.grabbites.databinding.ItemFoodCardBinding
import com.ionify.grabbites.utils.FoodDiffCallback

class RecommendationAdapter :
    RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {
    private val listFood = ArrayList<FoodListItem>()
    private var promo = ""
    private var onItemClickCallback: OnItemClickCallback? = null


    inner class RecommendationViewHolder(private val binding: ItemFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodListItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(food.image)
                    .into(ivFood)
                if (food.fakePrice == 0) {
                    tvFakePrice.visibility = View.GONE
                } else {
                    val fakePriceText = "Rp${food.fakePrice}"
                    val spanFakePrice = SpannableString(fakePriceText)
                    val strikeThroughSpan = StrikethroughSpan()

                    spanFakePrice.setSpan(
                        strikeThroughSpan,
                        0,
                        spanFakePrice.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    tvFakePrice.text = spanFakePrice
                }

                tvRealPrice.text = "Rp${food.realPrice}"
                if(promo.isNotEmpty()) {
                    tvDiscount.text = promo
                } else {
                    tvDiscount.visibility = View.GONE
                }
                tvFoodTitle.text = food.name

                val ratingText = "${food.ratingTotal}/5"
                val spanRating = SpannableString(ratingText)
                val primaryColor = itemView.context.getColor(R.color.primary_color)
                spanRating.setSpan(
                    ForegroundColorSpan(primaryColor),
                    0,
                    2,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tvRating.text = spanRating

                tvRestaurantTitle.text = food.restaurantName
                if (food.desc == "") {
                    tvCopywriting.visibility = View.GONE
                } else {
                    tvCopywriting.visibility = View.VISIBLE
                    tvCopywriting.text = food.desc
                }

                vpReview.visibility = View.VISIBLE
                dotsIndicator.visibility = View.VISIBLE

                val reviewAdapter = ReviewAdapter(food.userReview)
                vpReview.adapter = reviewAdapter
                dotsIndicator.attachTo(vpReview)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding =
            ItemFoodCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(listFood[position])
    }

    fun setListFood(listFood: ArrayList<FoodListItem>, promo: String) {
        val diffCallback = FoodDiffCallback(this.listFood, listFood)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFood.clear()
        this.listFood.addAll(listFood)
        this.promo = promo
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickCallback {
        fun onItemClicked(food: FoodListItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}