package com.app.kattabozor.di

import com.app.kattabozor.api.MyServiceAPi
import com.app.kattabozor.model.Offers
import com.app.kattabozor.model.OffersResponce
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val myServiceAPi: MyServiceAPi
) {
    suspend fun getProducts(): Response<OffersResponce>{
        return myServiceAPi.getAllData()
    }
}