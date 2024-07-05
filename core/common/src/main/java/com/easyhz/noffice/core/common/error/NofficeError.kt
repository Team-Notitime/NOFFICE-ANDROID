package com.easyhz.noffice.core.common.error

import android.util.Log

sealed class NofficeError: Throwable() {
    /* 예상하지 못한 에러 */
    data object UnexpectedError : NofficeError() {
        @JvmStatic
        private fun readResolve(): Any = UnexpectedError
        override fun printStackTrace() {
            Log.e("AppError", "예상치 못한 에러가 발생했습니다.")
        }
    }

    /* network 에러 */
    data object NetworkConnectionError : NofficeError() {
        @JvmStatic
        private fun readResolve(): Any = NetworkConnectionError
        override fun printStackTrace() {
            Log.e("AppError", "네트워크 오류가 발생했습니다.")
        }
    }
}