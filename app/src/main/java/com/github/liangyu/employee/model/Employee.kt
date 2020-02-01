package com.github.liangyu.employee.model

data class Employee(
    val uuid: String,
    val biography: String,
    val fullName: String,
    val phoneNumber: String,
    val photoUrlLarge: String,
    val photoUrlSmall: String
)