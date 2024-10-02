package com.easyhz.noffice.data.auth.util

import com.easyhz.noffice.core.network.model.request.sign.LoginRequest

const val google = "GOOGLE"
const val kakao = "KAKAO"

enum class Provider(
    val type: String
) {
    GOOGLE(type = google),
    KAKAO(type = kakao);

    fun getLoginRequest(code: String) = LoginRequest(
        code = code,
        provider = this.type,
    )
}