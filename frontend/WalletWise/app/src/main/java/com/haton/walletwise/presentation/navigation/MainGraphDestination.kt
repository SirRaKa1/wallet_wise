package com.haton.walletwise.presentation.navigation

private val ROUTE = MainNavGraph.Main.destination

private enum class Destination(val destination: String) {
    AUTHORIZATION("${ROUTE}_authorization"),
    MAIN("${ROUTE}_main"),
}

sealed class MainGraphDestination(val destination: String) {
    data object Authorization : MainGraphDestination(Destination.AUTHORIZATION.destination)
    data object Main : MainGraphDestination(Destination.MAIN.destination)
}
