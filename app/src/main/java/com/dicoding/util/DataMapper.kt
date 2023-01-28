package com.dicoding.util

import com.dicoding.core.data.source.local.entity.FavoriteUser
import com.dicoding.core.data.source.remote.response.ModelDet
import com.dicoding.core.domain.model.ItemResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by Rahmat Hidayat on 07/01/2023.
 */
object DataMapper {
    fun mapResponsesToDomain(input: List<ModelDet>):Flow<List<ItemResult>> {
        val dataArray = ArrayList<ItemResult>()
        input.map {
            val item = ItemResult(
                it.id,
                it.login,
                it.url,
                it.avatarUrl,
                it.name,
                it.location,
                it.type,
                it.publicRepos,
                it.followers,
                it.following,
                false

            )
            dataArray.add(item)
        }
        return flowOf()
    }

    fun mapResponseToDomain(input: ModelDet): Flow<ItemResult> {
        return flowOf(
            ItemResult(
                input.id,
                input.login,
                input.url,
                input.avatarUrl,
                input.name,
                input.location,
                input.type,
                input.publicRepos,
                input.followers,
                input.following,
                false
            )
        )
    }

    fun mapEntitiesToDomain(input: List<FavoriteUser>): List<ItemResult> =
        input.map { userEntity ->
            ItemResult(
                userEntity.id,
                userEntity.login,
                userEntity.url,
                userEntity.avatarUrl,
                userEntity.name,
                userEntity.location,
                userEntity.type,
                userEntity.publicRepos,
                userEntity.followers,
                userEntity.following,
                userEntity.isFavorite
            )
        }

    fun mapEntityToDomain(input: FavoriteUser?): ItemResult {
        return ItemResult(
            input?.id,
            input?.login,
            input?.url,
            input?.avatarUrl,
            input?.name,
            input?.location,
            input?.type,
            input?.publicRepos,
            input?.followers,
            input?.following,
            input?.isFavorite
        )
    }
    fun mapDomainToEntity(input: ItemResult) = FavoriteUser(
        input.id,
        input.login,
        input.url,
        input.avatarUrl,
        input.name,
        input.location,
        input.type,
        input.publicRepos,
        input.followers,
        input.following,
        input.isFavorite
    )
}