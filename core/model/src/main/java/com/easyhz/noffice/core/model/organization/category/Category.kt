package com.easyhz.noffice.core.model.organization.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val title: String,
    val isSelected: Boolean
)