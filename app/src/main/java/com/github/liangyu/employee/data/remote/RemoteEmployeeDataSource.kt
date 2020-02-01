package com.github.liangyu.employee.data.remote

import com.github.liangyu.employee.common.Result
import com.github.liangyu.employee.data.EmployeeDataSource
import com.github.liangyu.employee.data.remote.network.EmployeeService
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity

class RemoteEmployeeDataSource(
    private val employeeService: EmployeeService
) : EmployeeDataSource {

    override suspend fun getEmployees(): Result<List<EmployeeEntity>> {
        return try {
            val employeesResponse = employeeService.getEmployeesAsync()
            Result.Success(employeesResponse.employees)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
