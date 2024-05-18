package com.haton.walletwise.presentation.navigation.graph.authorization

sealed class AuthorizationNavGraph(val destination: String) {
    data object Authorization : AuthorizationNavGraph("authAuthorizationNav")
}
