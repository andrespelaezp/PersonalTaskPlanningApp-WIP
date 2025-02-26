package com.andrespelaezp.datasourcecompiler.api.data.openproject

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Int,
    @SerializedName("_type")
    val type: String,
    val login: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val avatar: String,
    val status: String
)