package com.dicoding.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.core.data.source.local.entity.FavoriteUser

@Database(entities = [FavoriteUser::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun favoriteUserDao(): FavoriteUserDao
}