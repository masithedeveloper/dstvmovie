package com.example.dstvmovie.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dstvmovie.compose.ui.detail.DetailScreen
import com.example.dstvmovie.compose.ui.home.HomeScreen

@Preview
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.MAIN) {
        composable(route = Route.MAIN) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Route.MOVIE_DETAIL,
            arguments = listOf(navArgument(Route.Param.MOVIE_ID) {
                type = NavType.Companion.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                itemId = backStackEntry.arguments?.getString(Route.Param.MOVIE_ID)
            )
        }
    }
}

object Route {
    const val MAIN = "main"
    const val MOVIE_DETAIL = "movieDetail/{${Param.MOVIE_ID}}"

    object Param {
        const val MOVIE_ID = "movieId"

        fun toPath(param: String) = "{${param}}"
    }
}

fun NavController.toMovieDetail(movieId: String?) {
    navigate(
        route = Route.MOVIE_DETAIL.replace(
            Route.Param.toPath(Route.Param.MOVIE_ID),
            movieId ?: ""
        )
    )
}