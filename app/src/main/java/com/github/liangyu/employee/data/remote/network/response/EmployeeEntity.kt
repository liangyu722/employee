package com.github.liangyu.employee.data.remote.network.response


import com.google.gson.annotations.SerializedName

data class EmployeeEntity(
    val biography: String,
    @SerializedName("email_address")
    val emailAddress: String,
    @SerializedName("employee_type")
    val employeeType: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String,
    val team: String,
    val uuid: String
)