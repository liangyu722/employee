package com.github.liangyu.employee.data

import com.github.liangyu.employee.common.DataLoadingException
import com.github.liangyu.employee.common.Result
import com.github.liangyu.employee.common.Result.Error
import com.github.liangyu.employee.common.Result.Success
import com.github.liangyu.employee.data.cache.EmployeeCache
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultEmployeeRepository @Inject constructor(
    private val remoteEmployeeDataSource: EmployeeDataSource,
    private val employeeCache: EmployeeCache,
    private val backgroundDispatcher: CoroutineDispatcher
) : EmployeeRepository {

    override suspend fun getEmployees(): Result<List<EmployeeEntity>> {
        return withContext(backgroundDispatcher) {
            val cachedEmployees = employeeCache.getEmployees()
            if (cachedEmployees.isNotEmpty()) {
                return@withContext Success(cachedEmployees)
            }
            return@withContext when (val remoteResponse = remoteEmployeeDataSource.getEmployees()) {
                is Success -> {
                    employeeCache.setEmployees(remoteResponse.data)
                    Success(employeeCache.getEmployees())
                }
                is Error -> {
                    Error((DataLoadingException("Cannot load employees")))
                }
            }
        }
    }
}
