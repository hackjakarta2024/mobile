package com.ionify.grabbites.utils

import androidx.recyclerview.widget.DiffUtil
import com.ionify.grabbites.data.model.FoodListItem

class FoodDiffCallback(
    private val oldFoodList: ArrayList<FoodListItem>,
    private val newFoodList: ArrayList<FoodListItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFoodList.size

    override fun getNewListSize(): Int = newFoodList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldFoodList[oldItemPosition] == newFoodList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFood = oldFoodList[oldItemPosition]
        val newFood = newFoodList[newItemPosition]
        return (
                oldFood.id == newFood.id &&
                oldFood.name == newFood.name &&
                oldFood.restaurantName == newFood.restaurantName &&
                oldFood.desc == newFood.desc &&
                oldFood.fakePrice == newFood.fakePrice &&
                oldFood.realPrice == newFood.realPrice &&
                oldFood.image == newFood.image &&
                oldFood.ratingTotal == newFood.ratingTotal
        )
    }

}