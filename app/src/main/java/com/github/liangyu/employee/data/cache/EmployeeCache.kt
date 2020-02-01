package com.github.liangyu.employee.data.cache

import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity

interface EmployeeCache {

    suspend fun setEmployees(employeeList: List<EmployeeEntity>)

    suspend fun getEmployees(): List<EmployeeEntity>

}