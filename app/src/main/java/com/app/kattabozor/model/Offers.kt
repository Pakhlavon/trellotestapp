package com.app.kattabozor.model

import com.google.gson.annotations.SerializedName

class Offers {
     @SerializedName("id")
     val id: Int = 0

     @SerializedName("name")
     val name: String = ""

     @SerializedName("brand")
     val brand: String =""

     @SerializedName("category")
     val category: String= ""

     @SerializedName("merchant")
     val merchant: String = ""

     @SerializedName("attributes")
     lateinit var attributes: List<Attributes>

     @SerializedName("image")
     lateinit var image: Image
}
