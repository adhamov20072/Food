package com.aimardon.food.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aimardon.food.network.NetworkApi

class HomeViewModelFactory(val networkApi: NetworkApi,val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(networkApi,application) as T
        }
        throw IllegalAccessException("")
    }
}