package com.github.liangyu.employee.data.remote.network

import com.github.liangyu.employee.data.remote.network.response.EmployeeResponse
import retrofit2.http.GET

interface EmployeeService {

    @GET("employees.json")
    //@GET("employees_malformed.json")
    //@GET("employees_empty.json")
    suspend fun getEmployeesAsync(): EmployeeResponse
}