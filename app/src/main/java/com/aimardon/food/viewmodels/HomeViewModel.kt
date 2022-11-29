package com.aimardon.food.viewmodels

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aimardon.food.network.NetworkApi
import com.aimardon.food.network.NetworkResult
import kotlinx.coroutines.launch

class HomeViewModel(private val networkApi: NetworkApi, application: Application) :
    AndroidViewModel(application) {
    val food:MutableLiveData<NetworkResult<List<com.aimardon.food.models.Result>>> = MutableLiveData()
    @RequiresApi(Build.VERSION_CODES.M)
    fun get(number: Int, addRecipeInformation: Boolean, apiKey: String)=viewModelScope.launch{
        getSafeCall(number,addRecipeInformation,apiKey)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getSafeCall (number: Int, addRecipeInformation: Boolean, apiKey: String){
        food.value = NetworkResult.Loading()
        if (hasInternetConnection()){
            val response=networkApi.getInformation(number,addRecipeInformation,apiKey)
            if (response.isSuccessful){
                food.value=NetworkResult.Success(response.body()?.results)
            }else{
                food.value=NetworkResult.Error(response.message())
            }
        }else{
            food.value= NetworkResult.Error("No Network Connection")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}