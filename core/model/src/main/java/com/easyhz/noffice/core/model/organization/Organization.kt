package com.easyhz.noffice.core.model.organization

data class Organization(
    val id: Int,
    val name: String,
    val profileImageUrl: String?,
    val role: String?,
    val joinStatus: JoinStatus?,
)

enum class JoinStatus {
    ACTIVE, PENDING, REJECTED, DELETED
}
