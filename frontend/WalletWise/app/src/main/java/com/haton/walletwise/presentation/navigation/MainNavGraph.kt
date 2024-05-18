package com.haton.walletwise.presentation.navigation

sealed class MainNavGraph(val destination: String) {
    data object Main : MainNavGraph("mainMainNav")
}
