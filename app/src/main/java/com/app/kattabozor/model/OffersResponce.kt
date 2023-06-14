package com.app.kattabozor.model

import com.google.gson.annotations.SerializedName

class OffersResponce {
    @SerializedName("offers")
    var offers: List<Offers> = emptyList()
}