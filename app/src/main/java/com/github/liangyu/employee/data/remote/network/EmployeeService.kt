package com.github.liangyu.employee.data.remote.network

import com.github.liangyu.employee.data.remote.network.response.EmployeeResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EmployeeService {

    @GET("employees.json")
    fun employeesAsync(): Deferred<EmployeeResponse>
}