package com.app.kattabozor.api

import com.app.kattabozor.model.Offers
import com.app.kattabozor.model.OffersResponce
import com.google.android.material.shape.OffsetEdgeTreatment
import retrofit2.Response
import retrofit2.http.GET

interface MyServiceAPi {

    @GET("offers")
    suspend fun getAllData() : Response<OffersResponce>
}