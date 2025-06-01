package com.example.landwapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.landwapp.ui.screens.FeldstueckListScreen

class FeldstueckComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeldstueckListScreen()
        }
    }
}
