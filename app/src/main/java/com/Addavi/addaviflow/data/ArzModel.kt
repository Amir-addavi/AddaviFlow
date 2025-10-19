package com.Addavi.addaviflow.data

import com.google.gson.annotations.SerializedName

data class ArzModel(
    @SerializedName("id")
    val id : Int,

    @SerializedName("price")
    val price : String
)