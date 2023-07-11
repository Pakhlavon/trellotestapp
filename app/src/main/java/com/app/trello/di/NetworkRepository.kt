package com.app.trello.di

import com.app.trello.api.MyServiceAPi
import com.app.trello.model.AllTasksResponse
import com.app.trello.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val myServiceAPi: MyServiceAPi
) {
    suspend fun login(body: RequestBody): Response<LoginResponse>{
        return myServiceAPi.login(params = body)
    }

    suspend fun getAllPosts(header: Map<String, Any>): Response<AllTasksResponse>{
        return myServiceAPi.getAllPosts(header = header)
    }
}