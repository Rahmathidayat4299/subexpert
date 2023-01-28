package com.dicoding.core.data.source.remote

import android.util.Log
import androidx.room.Query
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.network.ApiUser
import com.dicoding.core.data.source.remote.response.ModelDet
import com.dicoding.core.data.source.remote.response.UserResult
import com.dicoding.core.domain.model.ItemResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Rahmat Hidayat on 07/01/2023.
 */
class RemoteDataSource(private val apiUser: ApiUser) {
    suspend fun getAllUsers(query: String?): Flow<ApiResponse<List<ModelDet>>> =
        flow {
            try {
                val userSearch = apiUser.getListUser(query)
                val userArray = userSearch.items
                if (userArray.isNullOrEmpty()) {
                    emit(ApiResponse.Error(null))
                } else {
                    emit(ApiResponse.Success(userArray))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getFollowing(username: String): Flow<ApiResponse<List<ModelDet>>> =
        flow {

            try {
                val userFollowing = apiUser.pathFollow(username, "following")
                emit(ApiResponse.Success(userFollowing))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }

        }.flowOn(Dispatchers.IO)

    suspend fun getFollower(username: String): Flow<ApiResponse<List<ModelDet>>> =
        flow {
            try {
                val userFollowing = apiUser.pathFollow(username, "follower")
                emit(ApiResponse.Success(userFollowing))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getUserDetail(username: String): Flow<ApiResponse<ModelDet>> =
        flow {
            try {
                val userDetail = apiUser.userDetail(username)
                emit(ApiResponse.Success(userDetail))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)
}