package com.aimardon.food.models

data class Food(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)