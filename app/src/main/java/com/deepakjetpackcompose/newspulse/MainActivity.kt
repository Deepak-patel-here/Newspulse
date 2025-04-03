package com.deepakjetpackcompose.newspulse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deepakjetpackcompose.newspulse.navigation.Navigation
import com.deepakjetpackcompose.newspulse.ui.theme.NewspulseTheme
import com.deepakjetpackcompose.newspulse.viewmodel.AuthViewModel
import com.deepakjetpackcompose.newspulse.viewmodel.NewsViewModel
import com.deepakjetpackcompose.newspulse.viewmodel.ThemeViewModel


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeViewModel: ThemeViewModel by viewModels()
            val isDarkMode = themeViewModel.darkMode.observeAsState(false)
            val authViewModel: AuthViewModel by viewModels()
            val newsViewModel: NewsViewModel by viewModels()
            NewspulseTheme(darkTheme = isDarkMode.value) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(authViewModel = authViewModel, newsViewModel = newsViewModel, themeViewModel = themeViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

