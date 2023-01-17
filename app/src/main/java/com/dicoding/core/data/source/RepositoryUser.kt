package com.dicoding.core.data.source

import com.dicoding.core.data.NetworkOnlyResource
import com.dicoding.core.data.source.local.room.LocalDataSource
import com.dicoding.core.data.source.remote.RemoteDataSource
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.ModelDet
import com.dicoding.core.domain.model.ItemResult
import com.dicoding.core.domain.repository.IRepository
import com.dicoding.util.DataMapper
import com.dicoding.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rahmat Hidayat on 17/01/2023.
 */
class RepositoryUser(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):IRepository {
    override fun getAllUsers(query: String?): Flow<Resource<List<ItemResult>>> {
        return object : NetworkOnlyResource<List<ItemResult>,List<ModelDet>>(){
            override fun loadFromNetwork(data: List<ModelDet>): Flow<List<ItemResult>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ModelDet>>> {
                return remoteDataSource.getAllUsers(query)
            }

        }.asFlow()
    }

    override fun getAllFollowers(username: String): Flow<Resource<List<ItemResult>>> {
        TODO("Not yet implemented")
    }

    override fun getAllFollowing(username: String): Flow<Resource<List<ItemResult>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailUser(username: String): Flow<Resource<ItemResult>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteUser(): Flow<List<ItemResult>> {
        TODO("Not yet implemented")
    }

    override fun getDetailState(username: String): Flow<ItemResult>? {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: ItemResult) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: ItemResult): Int {
        TODO("Not yet implemented")
    }
}