package com.github.liangyu.employee.data

import com.github.liangyu.employee.common.Result
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity

interface EmployeeDataSource {

    suspend fun getEmployees(): Result<List<EmployeeEntity>>
}
