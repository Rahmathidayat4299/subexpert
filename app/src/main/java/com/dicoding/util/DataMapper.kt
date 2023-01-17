package com.dicoding.util

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
        }
        return flowOf()
    }
}