package com.example.imdbclone.retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val queryInterceptor = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", "95d0c12cbf616fde9f7b1cc1b62ac634").build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    private var gson: Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    private val retrofit = Retrofit.Builder().client(queryInterceptor).baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    val service = retrofit.create(TmdbApiInterface::class.java)
}