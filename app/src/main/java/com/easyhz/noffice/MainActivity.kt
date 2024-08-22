package com.easyhz.noffice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.easyhz.noffice.core.common.manager.DeepLinkManager
import com.easyhz.noffice.core.common.util.Encryption
import com.easyhz.noffice.core.design_system.theme.NofficeTheme
import com.easyhz.noffice.navigation.rememberNofficeNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        handleDeepLink(intent)
        setContent {
            val nofficeNavController = rememberNofficeNavController()
            NofficeTheme {
                NofficeApp(nofficeNavController)
            }
        }
    }

    private fun handleDeepLink(intent: Intent?) {
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if (data?.host != "join") return
        if (action == Intent.ACTION_VIEW) {
            val organizationId = data.getQueryParameter("organizationId") ?: return
            val id = Encryption.decrypt(organizationId).toIntOrNull() ?: return
            DeepLinkManager.setOrganizationIdToJoin(id)
        }
    }
}