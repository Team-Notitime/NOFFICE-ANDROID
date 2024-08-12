package com.easyhz.noffice.feature.my_page.contract.detail.consent

import com.easyhz.noffice.core.common.base.UiState

data class ConsentState(
    val isChecked: Boolean
): UiState() {
    companion object {
        fun init() = ConsentState(
            isChecked = false
        )
    }
}
