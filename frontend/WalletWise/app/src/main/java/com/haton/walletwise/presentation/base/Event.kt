package com.haton.walletwise.presentation.base

interface Event<T> {
    fun send(event: T)
}
