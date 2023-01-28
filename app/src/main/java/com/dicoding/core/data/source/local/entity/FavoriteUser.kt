package com.dicoding.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_user")
data class FavoriteUser(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "login")
    var login: String?,

    @ColumnInfo(name = "html_url")
    var url: String?,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "location")
    var location: String?,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "public_repos")
    var publicRepos: Int?,

    @ColumnInfo(name = "followers")
    var followers: Int?,

    @ColumnInfo(name = "following")
    var following: Int?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?
) : Parcelable
