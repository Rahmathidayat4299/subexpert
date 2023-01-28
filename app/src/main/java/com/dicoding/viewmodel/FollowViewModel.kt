package com.dicoding.viewmodel

import androidx.lifecycle.*
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.core.domain.usecase.UseCaseUser
import com.dicoding.util.Resource
import com.dicoding.util.TypeView

/**
 * Created by Rahmat Hidayat on 28/01/2023.
 */
class FollowViewModel(userUseCase: UseCaseUser) : ViewModel() {
    private var username: MutableLiveData<String> = MutableLiveData()
    private lateinit var typeView: TypeView

    fun setFollow(user: String, type: TypeView) {
        if (username.value == user) {
            return
        }
        username.value = user
        typeView = type
    }

    val favoriteUsers: LiveData<Resource<List<ItemResult>>> = Transformations
        .switchMap(username) {
            when (typeView) {
                TypeView.FOLLOWER -> userUseCase.getAllFollowers(it).asLiveData()
                TypeView.FOLLOWING -> userUseCase.getAllFollowing(it).asLiveData()
            }
        }
}