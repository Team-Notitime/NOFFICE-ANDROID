package com.easyhz.noffice.core.model.notice

import kotlinx.serialization.Serializable

/**
 * 마이페이지 - 공지사항
 * @param title 제목
 * @param content 내용
 * @param date "yyyy.MM.dd"
 */
@Serializable
data class Notice(
    val title: String,
    val content: String,
    val date: String
)
