package com.dicoding.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.core.data.source.local.entity.FavoriteUser
import com.dicoding.core.data.source.local.room.FavoriteUserDao
import com.dicoding.core.data.source.local.room.UserDatabase

class FavUserVm(application: Application) : AndroidViewModel(application) {
    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)

    init {
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavoriteUser()

    }
}