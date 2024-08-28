package com.easyhz.noffice

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyhz.noffice.domain.organization.usecase.organization.HandleDeepLinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val handleDeepLinkUseCase: HandleDeepLinkUseCase
): ViewModel() {

    fun handleIntent(intent: Intent?): Deferred<Int> = viewModelScope.async {
        if (intent == null) return@async -1
        return@async handleDeepLinkUseCase(intent).getOrNull() ?: -1
    }
}