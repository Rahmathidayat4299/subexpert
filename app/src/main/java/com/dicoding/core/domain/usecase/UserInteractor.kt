package com.dicoding.core.domain.usecase

import com.dicoding.core.domain.model.ItemResult
import com.dicoding.core.domain.repository.IRepository
import com.dicoding.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rahmat Hidayat on 05/01/2023.
 */
class UserInteractor(private val repository: IRepository):UseCaseUser {
    override fun getAllUsers(query: String?): Flow<Resource<List<ItemResult>>> = repository.getAllUsers(query)

    override fun getAllFollowers(username: String): Flow<Resource<List<ItemResult>>> = repository.getAllFollowers(username)

    override fun getAllFollowing(username: String): Flow<Resource<List<ItemResult>>> = repository.getAllFollowing(username)

    override fun getDetailUser(username: String): Flow<Resource<ItemResult>> = repository.getDetailUser(username)

    override fun getFavoriteUser(): Flow<List<ItemResult>> = repository.getFavoriteUser()

    override fun getDetailState(username: String): Flow<ItemResult>? = repository.getDetailState(username)

    override suspend fun insertUser(user: ItemResult) = repository.insertUser(user)

    override suspend fun deleteUser(user: ItemResult): Int = repository.deleteUser(user)

}