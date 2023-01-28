package com.dicoding.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.core.data.source.local.entity.FavoriteUser
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFavorite(favoriteUser: FavoriteUser?)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): Flow<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    fun checkUser(id: Int): Int
    @Delete
    suspend fun deleteUser(user: FavoriteUser): Int

//    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
//    fun removeFromFavorite(id: Int): Int
    @Query("SELECT * FROM favorite_user WHERE login = :username")
    fun getDetailState(username: String): Flow<FavoriteUser>?
}