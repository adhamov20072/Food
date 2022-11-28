package com.aimardon.food.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkApi {
    @GET("/complexSearch")
    suspend fun getInformation(
        @Query("diet") diet: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("apiKey") apiKey: String
    ): Response<List<com.aimardon.food.models.Result>>
}