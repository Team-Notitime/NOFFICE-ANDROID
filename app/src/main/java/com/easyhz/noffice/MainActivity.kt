package com.easyhz.noffice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.easyhz.noffice.core.common.manager.DeepLinkManager
import com.easyhz.noffice.core.design_system.theme.NofficeTheme
import com.easyhz.noffice.navigation.home.navigateToHome
import com.easyhz.noffice.navigation.rememberNofficeNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var navController: NavHostController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        handleDeepLink(intent)
        setContent {
            val nofficeNavController = rememberNofficeNavController()
            navController = nofficeNavController.navController
            NofficeTheme {
                NofficeApp(nofficeNavController)
            }
        }
    }

    private fun handleDeepLink(intent: Intent?) = lifecycleScope.launch {
        val organizationId = viewModel.handleIntent(intent).await()
        if (organizationId == -1) return@launch
        DeepLinkManager.setOrganizationIdToJoin(organizationId)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
        val navOptions = navOptions {
            navController?.graph?.id?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
        navController?.navigateToHome(navOptions)
    }
}