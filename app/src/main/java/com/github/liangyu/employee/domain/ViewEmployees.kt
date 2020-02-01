package com.github.liangyu.employee.domain

import com.github.liangyu.employee.common.Result
import com.github.liangyu.employee.model.Employee

interface ViewEmployees {

    suspend fun getEmployees(): Result<List<Employee>>

}