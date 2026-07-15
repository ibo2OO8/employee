package com.ibrohimapk3.employeelist.domain.error

sealed class AppException(message: String) : RuntimeException(message) {
    class NetworkException : AppException("нету интернета")

    class EmptyException : AppException("нет результата")

    class InvalidEmailOrPassword : AppException("Неверный логин или пароль")
}