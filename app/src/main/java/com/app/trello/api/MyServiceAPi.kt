package com.app.trello.api

import com.app.trello.model.AllTasksResponse
import com.app.trello.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface MyServiceAPi {

    @POST("api/v2/auth/login")
    suspend fun login(@Body params: RequestBody) : Response<LoginResponse>

    @GET("api/v1/task/get_all_tasks")
    suspend fun getAllPosts( @HeaderMap header: Map<String, Any>): Response<AllTasksResponse>
}