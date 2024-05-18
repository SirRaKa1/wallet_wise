package com.haton.walletwise.presentation.navigation.graph.main

sealed class HomeNavGraph(val destination: String) {
    data object Home : HomeNavGraph("homeNav")
}
