package com.github.liangyu.employee.data.remote.network

import com.github.liangyu.employee.data.remote.network.response.EmployeeResponse
import retrofit2.http.GET

interface EmployeeService {

    @GET("employees.json")
    suspend fun getEmployeesAsync(): EmployeeResponse
}