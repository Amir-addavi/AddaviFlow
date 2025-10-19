package com.Addavi.addaviflow.data.remote

import com.Addavi.addaviflow.data.ArzModel
import retrofit2.http.GET

interface ArzService{
    @GET("?get=arz")
    suspend fun getArzList() : List<ArzModel>
}