package com.example.mvvmormviwithhilt.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//this is a entity model
data class BlogNetworkEntity(

    @SerializedName("pk")
    @Expose
    var id:Int,

    @SerializedName("title")
    @Expose
    var title:String,

    @SerializedName("body")
    @Expose
    var body:String,

    @SerializedName("image")
    @Expose
    var image:String,

    @SerializedName("category")
    @Expose
    var category:String,

) {
}