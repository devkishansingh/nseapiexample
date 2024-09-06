package com.devkishan.nseapi.api


import com.devkishan.nseapi.model.AllIndices
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface NseApi{
//     @GET("photos")
////     fun getPhotos(): Call<List<Photo>>

//     @GET("photos")
//    suspend fun getPhotos(): List<Photo>
    @GET("allIndices")
    suspend fun getAllIndices(): List<AllIndices>

    @DELETE("allIndices/{key}")
    suspend fun deleteIndex(@Path("key") key: String)

//    @POST("photos")
//    suspend fun createPhoto(@Body photo: Photo): Photo
//
//    @DELETE("photos/{id}")
//    suspend fun deletePhoto(@Path("id") id: Int)

}