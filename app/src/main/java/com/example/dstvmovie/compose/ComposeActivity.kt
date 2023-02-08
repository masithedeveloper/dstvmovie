package com.example.dstvmovie.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dstvmovie.compose.theme.ComposeAppTheme
import com.example.dstvmovie.compose.ui.home.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApp()
        }
    }
}

@Composable
fun ComposeApp() {
    ComposeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainNavigation()
        }

//        Surface(color = Color.White) {
//            Scaffold(
//                bottomBar = {
//                    BottomNavigationBar()
//                }, content = { padding ->
//                    // Navhost: where screens are placed
//                    NavHostContainer(navController = navController, padding = padding)
//                }
//            )
//        }
    }

}