package com.onlineShop.app.models.customers

data class User(
    var id: Long,
    var customer: Customer?,
    val password: String?,
    val username: String?
)
