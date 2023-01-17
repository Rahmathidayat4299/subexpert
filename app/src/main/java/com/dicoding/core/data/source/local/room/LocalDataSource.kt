package com.dicoding.core.data.source.local.room

import androidx.lifecycle.LiveData
import com.dicoding.core.data.source.local.entity.FavoriteUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rahmat Hidayat on 05/01/2023.
 */
class LocalDataSource(private val dao: FavoriteUserDao) {
    fun getFavoriteUser(): Flow<List<FavoriteUser>> = dao.getFavoriteUser()



    fun getDetailState(username: String): Flow<FavoriteUser>? = dao.getDetailState(username)

    suspend fun insertUser(user: FavoriteUser) = dao.addToFavorite(user)

    suspend fun deleteUser(user: FavoriteUser) = dao.deleteUser(user)
}