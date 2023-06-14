package com.app.kattabozor.di

import com.app.kattabozor.api.MyServiceAPi
import com.app.kattabozor.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val interceptor = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesMyServiceApi(retrofit: Retrofit): MyServiceAPi {
        return retrofit.create(MyServiceAPi::class.java)
    }
}