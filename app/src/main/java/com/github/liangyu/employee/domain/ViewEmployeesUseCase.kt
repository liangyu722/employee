package com.github.liangyu.employee.domain

import com.github.liangyu.employee.common.Result
import com.github.liangyu.employee.common.Result.Error
import com.github.liangyu.employee.common.Result.Success
import com.github.liangyu.employee.data.EmployeeRepository
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity
import com.github.liangyu.employee.model.Employee

class ViewEmployeesUseCase(
    private val repository: EmployeeRepository
) : ViewEmployees {

    override suspend fun getEmployees(): Result<List<Employee>> {
        return when (val result = repository.getEmployees()) {
            is Success -> {
                Success(result.data.map { employeeEntity ->
                    employeeEntity.toEmployee()
                })
            }
            is Error -> result
        }
    }

    private fun EmployeeEntity.toEmployee(): Employee {
        return Employee(
            this.uuid,
            this.fullName,
            this.phoneNumber,
            this.emailAddress,
            this.photoUrlSmall,
            this.team
        )
    }
}