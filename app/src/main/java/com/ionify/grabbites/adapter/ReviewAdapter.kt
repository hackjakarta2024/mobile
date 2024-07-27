package com.ionify.grabbites.adapter

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ionify.grabbites.R
import com.ionify.grabbites.data.model.UserReview
import com.ionify.grabbites.databinding.ItemReviewCardBinding

class ReviewAdapter(private val listReview: ArrayList<UserReview>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
//    private val listReview = ArrayList<UserReview>()

    inner class ReviewViewHolder(private val binding: ItemReviewCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: UserReview) {
            binding.apply {
                tvUserName.text = review.name

                val ratingText = "${review.rating}/5"
                val spanRating = SpannableString(ratingText)
                val primaryColor = itemView.context.getColor(R.color.primary_color)
                spanRating.setSpan(
                    ForegroundColorSpan(primaryColor),
                    0,
                    2,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                tvRating.text = spanRating
                tvUserReview.text = review.review
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            ItemReviewCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int = listReview.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(listReview[position])
    }
}