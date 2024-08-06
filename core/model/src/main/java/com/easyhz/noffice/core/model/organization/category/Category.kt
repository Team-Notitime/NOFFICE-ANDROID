package com.easyhz.noffice.core.model.organization.category

data class Category(
    val title: String,
    val isSelected: Boolean
)

// FIXME
val CATEGORY = listOf("IT 계열", "문화 생활", "어학", "예술", "음악 · 공연", "스터디 · 연구", "스포츠", "창업", "종교", "마케팅 · 홍보", "자연과학", "기타")

fun List<String>.toState(): List<Category> = map {
    Category(
        it, false
    )
}