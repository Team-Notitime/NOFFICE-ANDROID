package com.easyhz.noffice.data.organization.repository.deepLink

import android.content.Intent
import android.net.Uri

interface DeepLinkRepository {
    suspend fun createOrganizationDeepLink(organizationId: Int): Result<Uri>
    suspend fun handleDeepLink(intent: Intent): Result<Int>
}