package com.Addavi.addaviflow.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("ajax.json")
   suspend fun getdata() : DataModelRoot
}