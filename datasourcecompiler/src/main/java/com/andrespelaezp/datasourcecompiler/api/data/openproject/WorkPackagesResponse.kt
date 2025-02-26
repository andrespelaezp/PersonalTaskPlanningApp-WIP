package com.andrespelaezp.datasourcecompiler.api.data.openproject

import com.google.gson.annotations.SerializedName

data class WorkPackagesListResponse(
    val total: Int,
    @SerializedName("_embedded")
    val embedded: EmbeddedWorkPackages
)

data class EmbeddedWorkPackages(
    @SerializedName("elements")
    val workPackages: List<WorkPackage>
)

data class WorkPackage(
    val id: Int,
    val subject: String,
    val description: Description?,
    val status: String?,
    val createdAt: String,
    val updatedAt: String,
    val project: String?,
    val dueDate: String?
)

data class Description(
    val raw: String?
)
