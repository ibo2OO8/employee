package com.ibrohimapk3.employeelist.domain.test

sealed class Test(message: String) : RuntimeException(message) {
    class NetworkException : Test("нету интернета")

    class EmptyException : Test("нет результата")
}