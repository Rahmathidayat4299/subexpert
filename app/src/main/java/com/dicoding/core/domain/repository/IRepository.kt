package com.dicoding.core.domain.repository

import com.dicoding.core.domain.model.ItemResult
import com.dicoding.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rahmat Hidayat on 05/01/2023.
 */
interface IRepository {
    fun getAllUsers(query: String?): Flow<Resource<List<ItemResult>>>

    fun getAllFollowers(username: String): Flow<Resource<List<ItemResult>>>

    fun getAllFollowing(username: String): Flow<Resource<List<ItemResult>>>

    fun getDetailUser(username: String): Flow<Resource<ItemResult>>

    fun getFavoriteUser(): Flow<List<ItemResult>>

    fun getDetailState(username: String): Flow<ItemResult>?

    suspend fun insertUser(user: ItemResult)

    suspend fun deleteUser(user: ItemResult): Int
}