package com.easyhz.noffice.core.model.organization.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val title: String,
    val isSelected: Boolean
)

// FIXME
val CATEGORY = listOf("IT", "문화 생활", "어학", "예술", "음악 · 공연", "스터디 · 연구", "스포츠", "창업", "종교", "마케팅 · 홍보", "자연과학", "기타")

fun List<String>.toState(): List<Category> = map {
    Category(
        0,
        it, false
    )
}