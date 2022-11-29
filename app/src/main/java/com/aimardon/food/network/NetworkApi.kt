package com.aimardon.food.network

import com.aimardon.food.models.Food
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkApi {
    @GET("recipes/complexSearch")
    suspend fun getInformation(
        @Query("number") number: Int,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("apiKey") apiKey: String
    ): Response<Food>
}