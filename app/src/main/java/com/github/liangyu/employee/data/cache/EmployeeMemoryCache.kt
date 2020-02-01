package com.github.liangyu.employee.data.cache

import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity

class EmployeeMemoryCache : EmployeeCache {

    private var employeeListData = emptyList<EmployeeEntity>()

    override suspend fun setEmployees(employeeList: List<EmployeeEntity>) {
        employeeListData = employeeList
    }

    override suspend fun getEmployees() = employeeListData

}