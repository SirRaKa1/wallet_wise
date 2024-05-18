package com.haton.walletwise.presentation.navigation.graph.main

private val ROUTE = HomeNavGraph.Home.destination

private enum class Destination(val destination: String) {
    HOME("${ROUTE}_home"),
}

sealed class HomeGraphDescription(val destination: String) {
    data object Home : HomeGraphDescription(Destination.HOME.destination)
}
