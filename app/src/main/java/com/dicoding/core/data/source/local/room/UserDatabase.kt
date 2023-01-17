package com.dicoding.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.core.data.source.local.entity.FavoriteUser

@Database(entities = [FavoriteUser::class], version = 1)
//abstract class UserDatabase : RoomDatabase() {
//    companion object {
//        private var INSTANCE: UserDatabase? = null
//        fun getDatabase(context: Context): UserDatabase? {
//            if (INSTANCE == null) {
//                synchronized(UserDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        UserDatabase::class.java,
//                        "user_database"
//                    ).build()
//                }
//            }
//            return INSTANCE
//        }
//
//    }
//
//    abstract fun favoriteUserDao(): FavoriteUserDao
//}

abstract class UserDatabase: RoomDatabase() {
    abstract fun favoriteUserDao(): FavoriteUserDao
}