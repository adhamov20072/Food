package com.aimardon.food.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkApi {
    //complexSearch
    // ?diet=number=50
    // &
    // addRecipeInformation=true
    // &
    // apiKey=04ebf79f1e5a453b905f660a6f0b0eaa
    @GET("complexSearch")
    suspend fun getInformation(
        @Query("diet=number") diet: Int,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("apiKey") apiKey: String
    ): Response<List<com.aimardon.food.models.Result>>
}