package com.example.screen.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screen.ContactViewModel

@Composable
fun navigationExample() {
    val navController = rememberNavController()

    // Crear una sola instancia del ViewModel que será compartida
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    val contactViewModel = viewModel<ContactViewModel>(viewModelStoreOwner!!)

    NavHost(navController = navController, startDestination = "screen-a") {
        composable("screen-a") {
            ScreenA(navController = navController, contactViewModel = contactViewModel)
        }
        composable("screen-a/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toInt()
            ScreenA(navController = navController, contactViewModel = contactViewModel, contactIndex = index)
        }
        composable("screen-b") {
            ScreenB(navController = navController, contactViewModel = contactViewModel)
        }
    }
}
