package com.github.liangyu.employee.model

data class Employee(
    val uuid: String,
    val fullName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val photoUrlSmall: String,
    val team: String
)