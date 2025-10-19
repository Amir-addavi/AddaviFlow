package com.Addavi.addaviflow.data.remote.repository

import com.Addavi.addaviflow.data.ArzModel
import com.Addavi.addaviflow.data.remote.ArzService
import com.Addavi.addaviflow.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

sealed class Resource<out  T>{
    object Loading : Resource<Nothing>()
    data class Succes<T>(val data : T) : Resource<T>()
    data class Error(val message : String) : Resource<Nothing>()
}
    class ArzRepository(){
        private val service = RetrofitClient.arzService

        suspend fun fetchArzList(): Resource<List<ArzModel>>{
            return withContext(Dispatchers.IO){
                try {
                    val list = service.getArzList()
                    Resource.Succes(list)
                    }catch (e : Exception){
                    Resource.Error(e.message ?: "Unknown Error")
                }
            }
        }
}