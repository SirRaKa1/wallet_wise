package com.haton.walletwise.presentation.navigation

private val ROUTE = MainNavGraph.Main.destination

private enum class Destination(val destination: String) {
    AUTHORIZATION("${ROUTE}_authorization"),
}

sealed class MainGraphDestination(val destination: String) {
    data object Authorization : MainGraphDestination(Destination.AUTHORIZATION.destination)
}
