package com.haton.walletwise.presentation.navigation.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions

fun pageNavigation(
    navController: NavController,
    destination: String,
    incl: Boolean = true,
) {
    navController.navigate(
        destination,
        navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = incl
            }
            launchSingleTop = false
        },
    )
}
