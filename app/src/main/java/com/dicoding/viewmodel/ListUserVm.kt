package com.dicoding.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.core.data.source.remote.response.UserResult
import com.dicoding.core.domain.usecase.UseCaseUser
import com.dicoding.retrofit.RetroService
import com.dicoding.util.Resource
import org.koin.core.qualifier.Qualifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class ListUserVm(userUseCaseUser: UseCaseUser) : ViewModel() {
    private var username: MutableLiveData<String> = MutableLiveData()

    fun searchUser(query: String) {
        if (username.value == query) {
            return
        }
        username.value = query
    }

    val user: LiveData<Resource<List<ItemResult>>> = Transformations
        .switchMap(username) { userUseCaseUser.getAllUsers(it).asLiveData() }
}