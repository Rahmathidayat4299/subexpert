package com.dicoding.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.data.source.local.entity.FavoriteUser
import com.dicoding.core.data.source.local.room.FavoriteUserDao
import com.dicoding.core.data.source.local.room.UserDatabase
import com.dicoding.core.domain.usecase.UseCaseUser

class FavUserVm(userCaseUser: UseCaseUser) : ViewModel() {
    val favoriteUser = userCaseUser.getFavoriteUser().asLiveData()

}