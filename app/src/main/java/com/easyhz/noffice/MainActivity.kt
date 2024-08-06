package com.easyhz.noffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.easyhz.noffice.core.design_system.theme.NofficeTheme
import com.easyhz.noffice.navigation.rememberNofficeNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val nofficeNavController = rememberNofficeNavController()
            NofficeTheme {
                NofficeApp(nofficeNavController)
            }
        }
    }
}