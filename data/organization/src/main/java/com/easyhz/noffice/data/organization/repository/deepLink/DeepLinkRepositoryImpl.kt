package com.easyhz.noffice.data.organization.repository.deepLink

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.easyhz.noffice.core.common.util.Encryption
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.androidParameters
import com.google.firebase.dynamiclinks.ktx.iosParameters
import com.google.firebase.dynamiclinks.shortLinkAsync
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FIXME : migration 필요
 * Dynamic link 는 지원중단 됩니다. 2025/08/25
 * 현재 임시적으로 [Deprecated] 된 메서드를 사용함
 * 마이그레이션이 필요합니다
 * Created by easyhz on 2024/08/27.
 */
@Suppress("DEPRECATION")
class DeepLinkRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dynamicLinks: FirebaseDynamicLinks
): DeepLinkRepository {

    override suspend fun createOrganizationDeepLink(organizationId: Int): Result<Uri> =
        runCatching {
            val id = Encryption.encrypt(organizationId.toString())
            val url = "https://www.noffice.page.link/organization?token=$id"
            val dynamicLinks = dynamicLinks.shortLinkAsync {
                link = Uri.parse(url)
                domainUriPrefix = "https://noffice.page.link"
                androidParameters(context.packageName) {
                    fallbackUrl = Uri.parse("https://play.google.com/store/apps/details?id=com.easyhz.noffice.release")
                    minimumVersion = 1
                }
                iosParameters("noontime.noffice.app") {
                    appStoreId = "6529546973"
                    minimumVersion = "1.0.0"
                }
            }
            val links = dynamicLinks.await()
            links.shortLink ?: throw Exception("Failed to create dynamic link")
        }

    override suspend fun handleDeepLink(intent: Intent): Result<Int> {
        return runCatching {
            val id = dynamicLinks.getDynamicLink(intent).await()
                ?.link?.getQueryParameter("token")

            val organizationId = Encryption.decrypt(id.toString())?.toIntOrNull() ?: -1

            organizationId
        }
    }
}