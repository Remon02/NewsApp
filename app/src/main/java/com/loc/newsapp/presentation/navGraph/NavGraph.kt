package com.loc.newsapp.presentation.navGraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.presentation.home.HomeScreen
import com.loc.newsapp.presentation.home.HomeViewModel
import com.loc.newsapp.presentation.onBoarding.OnBoardingScreen
import com.loc.newsapp.presentation.onBoarding.OnBoardingViewModel
import com.loc.newsapp.presentation.search.SearchScreen
import com.loc.newsapp.presentation.search.SearchViewModel


@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,

        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route,

            ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewMode: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewMode::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                state = viewModel.state.value,
                event = viewModel::onEvent,
                navigate = {}

            ) 
            }
        }
    }
}