package com.example.myplayer.data.api

import android.util.Log
import com.example.myplayer.data.model.VideoListResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*
import java.util.concurrent.TimeUnit

val baseUrl = "https://www.googleapis.com/youtube/v3/"
interface ApiInterface {

    @GET("search?key=AIzaSyACzpdR-lIiVBEJ-paUfMZsQV4zlcda4R4&channelId=UCAptZHV2-xkB4GJGgSKRsHQ&part=snippet,id&order=date&maxResults=50")
    fun getVideoResult()
            :Observable<Response<VideoListResponse>>
    companion object Factory {
        fun create(): ApiInterface {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(
                    HttpLoggingInterceptor(
                        object : HttpLoggingInterceptor.Logger {
                            override fun log(message: String) {
                                Log.i("Video Service", ":$message")
                            }
                        }
                    )
                ).build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}