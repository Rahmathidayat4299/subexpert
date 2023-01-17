package com.dicoding.core.domain.model

import com.google.gson.annotations.SerializedName

data class ItemResult(
    val id: Int?,
    val login: String?,
    val url: String?,
    val avatarUrl: String?,
    val name: String?,
    val location: String?,
    val type: String?,
    val publicRepos: Int?,
    val followers: Int?,
    val following: Int?,
    var isFavorite: Boolean?
)
