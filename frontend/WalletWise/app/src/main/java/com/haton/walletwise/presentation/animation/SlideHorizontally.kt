package com.haton.walletwise.presentation.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right

class SlideHorizontally {
    context(AnimatedContentTransitionScope<NavBackStackEntry>)
    fun enterTransitionSlideIn() = slideIn(Left)

    context(AnimatedContentTransitionScope<NavBackStackEntry>)
    fun exitTransitionSlideOut() = slideOut(Left)

    context(AnimatedContentTransitionScope<NavBackStackEntry>)
    fun popEnterTransitionSlideIn() = slideIn(Right)

    context(AnimatedContentTransitionScope<NavBackStackEntry>)
    fun popExitTransitionSlideOut() = slideOut(Right)

    context(AnimatedContentTransitionScope<NavBackStackEntry>)
    private fun slideIn(direction: AnimatedContentTransitionScope.SlideDirection) =
        fadeIn(animationSpec = tween(300)) + slideIntoContainer(
            direction, tween(300)
        )

    context(AnimatedContentTransitionScope<NavBackStackEntry>)
    private fun slideOut(direction: AnimatedContentTransitionScope.SlideDirection) =
        fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
            direction, tween(300)
        )
}
