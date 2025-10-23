package com.Addavi.addaviflow.data

data class DataModel(
    val p : String,
    val d : String,
    val dt : String
)

data class DataModelRoot(
    val current : Map<String , DataModel>
)