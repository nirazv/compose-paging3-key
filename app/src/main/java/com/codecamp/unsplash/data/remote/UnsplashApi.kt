package com.codecamp.unsplash.data.remote

import retrofit2.http.GET

import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi {

    @GET("photos")
    @Headers("Authorization: Client-ID EC-_Fjb4e3PlHJbmZOCGGgwY9JcVfwhFFgCETZvbRQ8")
    suspend fun getUnsplashImageResult(
        @Query("page") page: Int,
        @Query("per_page") pageSize : Int
    ) : List<UnsplashDto>


    @GET("search/photos")
    @Headers("Authorization : Client-ID EC-_Fjb4e3PlHJbmZOCGGgwY9JcVfwhFFgCETZvbRQ8")
    suspend fun getUnsplashSearchResult(
        @Query("page") page: Int,
        @Query("per_page") pageSize : Int
    ) : List<UnsplashDto>


    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

}