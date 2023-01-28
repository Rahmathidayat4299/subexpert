package com.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResult(
    @field:SerializedName("items")
    val items: List<ModelDet>
)
