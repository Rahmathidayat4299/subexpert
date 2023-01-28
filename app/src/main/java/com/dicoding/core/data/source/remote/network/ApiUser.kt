package com.dicoding.core.data.source.remote.network

import com.dicoding.core.domain.model.ItemResult
import com.dicoding.core.data.source.remote.response.ModelDet
import com.dicoding.core.data.source.remote.response.UserResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUser {
    @GET("search/users")
    fun getListUser(
        @Query("q") query: String?
    ): UserResult

    @GET("users/{username}")
    fun userDetail(
        @Path("username") username: String
    ): ModelDet


    @GET("users/{username}/{type}")
    fun pathFollow(
        @Path("username") username: String,@Path("type") type: String,
    ): List<ModelDet>
}