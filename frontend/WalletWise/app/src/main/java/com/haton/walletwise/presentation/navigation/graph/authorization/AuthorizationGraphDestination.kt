package com.haton.walletwise.presentation.navigation.graph.authorization

private val ROUTE = AuthorizationNavGraph.Authorization.destination

private enum class Destination(val destination: String) {
    FIRST_ENTRY("${ROUTE}_firstEntry"),
    SIGN_IN("${ROUTE}_signIn"),
    SIGN_UP("${ROUTE}_signUp"),
}

sealed class AuthorizationGraphDestination(val destination: String) {
    data object FirstEntry : AuthorizationGraphDestination(Destination.FIRST_ENTRY.destination)
    data object SignIp : AuthorizationGraphDestination(Destination.SIGN_IN.destination)
    data object SignUp : AuthorizationGraphDestination(Destination.SIGN_UP.destination)
}