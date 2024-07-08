package com.easyhz.noffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.easyhz.noffice.core.design_system.theme.NofficeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NofficeTheme {
                NofficeApp()
            }
        }
    }
}