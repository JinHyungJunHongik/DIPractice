package com.example.dipractice

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


interface GetSearchDataResource {
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query : String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : SearchImageResponse

    @GET("v2/search/vclip")
    suspend fun getSearchVideo(
        @Query("query") query : String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ) : SearchVideoResponse
}

class SearchRepository @Inject constructor(private val getSearchDataResource: GetSearchDataResource) {
    suspend fun getSearchImage(query: String, sort: String, page: Int, size: Int) = getSearchDataResource.getSearchImage(query, sort, page, size)
}
