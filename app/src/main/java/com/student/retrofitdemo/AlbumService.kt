package com.student.retrofitdemo

import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<AlbumsItem>>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int): Response<List<AlbumsItem>>

    @GET("/albums/{id}")
    suspend fun getSortedAlbums2(@Path("id")id:Int): Response<List<AlbumsItem>>

    @POST("/albums")
    suspend fun uploadAlbum(@Body albumsItem: AlbumsItem): Response<List<AlbumsItem>>

}