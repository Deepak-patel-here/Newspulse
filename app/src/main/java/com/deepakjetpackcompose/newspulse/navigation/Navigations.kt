package com.deepakjetpackcompose.newspulse.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deepakjetpackcompose.newspulse.news.model.Articles
import com.deepakjetpackcompose.newspulse.pages.HomeScreen
import com.deepakjetpackcompose.newspulse.pages.Login
import com.deepakjetpackcompose.newspulse.pages.NewsSplash
import com.deepakjetpackcompose.newspulse.pages.SignUp
import com.deepakjetpackcompose.newspulse.screens.GeneralScreen
import com.deepakjetpackcompose.newspulse.screens.ReadScreen
import com.deepakjetpackcompose.newspulse.screens.ScienceScreen
import com.deepakjetpackcompose.newspulse.screens.Sports
import com.deepakjetpackcompose.newspulse.screens.TechScreen
import com.deepakjetpackcompose.newspulse.viewmodel.AuthViewModel
import com.deepakjetpackcompose.newspulse.viewmodel.NewsViewModel
import com.deepakjetpackcompose.newspulse.viewmodel.ThemeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(authViewModel: AuthViewModel,newsViewModel: NewsViewModel,themeViewModel: ThemeViewModel,modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable(route="login") {
            Login(navController = navController, authViewModel = authViewModel, modifier = modifier)
        }
        composable(route="signup") {
            SignUp(navController = navController, authViewModel = authViewModel, modifier = modifier)
        }
        composable(route="home") {
            HomeScreen(navController = navController, authViewModel = authViewModel, newsViewModel = newsViewModel, themeViewModel = themeViewModel, modifier = modifier)
        }
        composable(route="splash") {
            NewsSplash(navController=navController, modifier = modifier)
        }
        composable(
            route = "readScreen/{articleImg}/{articleTitle}/{articleDescription}",
            arguments = listOf(
                navArgument("articleImg") { type = NavType.StringType },
                navArgument("articleTitle") { type = NavType.StringType },
                navArgument("articleDescription") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val img = backStackEntry.arguments?.getString("articleImg") ?: ""
            val title = backStackEntry.arguments?.getString("articleTitle") ?: ""
            val description = backStackEntry.arguments?.getString("articleDescription") ?: ""

            ReadScreen(
                navController = navController,
                img = img,
                title = title,
                description = description
            )
        }


    }

}