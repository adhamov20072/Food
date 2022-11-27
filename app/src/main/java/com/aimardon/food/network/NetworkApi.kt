package com.aimardon.food.network

import com.aimardon.food.models.Food
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkApi {
    //716429/information?apiKey=04ebf79f1e5a453b905f660a6f0b0eaa&includeNutrition=true
    @GET("716429/information?apiKey=04ebf79f1e5a453b905f660a6f0b0eaa")
    suspend fun getInformation():Response<Food>
}