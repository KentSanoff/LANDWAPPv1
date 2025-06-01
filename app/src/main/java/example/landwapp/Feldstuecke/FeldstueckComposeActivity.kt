package com.example.landwapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.landwapp.feldstuecke.*
import com.example.landwapp.navigation.AppNavGraph
import com.example.landwapp.theme.Theme

class FeldstueckComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = FeldstueckRepository(
            AppDatabase.getDatabase(applicationContext).feldstueckDao()
        )

        setContent {
            Theme {
                val viewModel: FeldstueckViewModel = viewModel(
                    factory = viewModelFactory {
                        initializer { FeldstueckViewModel(repository) }
                    }
                )
                AppNavGraph()
            }
        }
    }
}
