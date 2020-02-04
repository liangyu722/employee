package com.github.liangyu.employee.di.repository

import com.github.liangyu.employee.data.DefaultEmployeeRepository
import com.github.liangyu.employee.data.EmployeeDataSource
import com.github.liangyu.employee.data.EmployeeRepository
import com.github.liangyu.employee.data.cache.EmployeeCache
import com.github.liangyu.employee.data.cache.EmployeeMemoryCache
import com.github.liangyu.employee.data.remote.RemoteEmployeeDataSource
import com.github.liangyu.employee.data.remote.network.EmployeeService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesDataSource(employeeService: EmployeeService): EmployeeDataSource {
        return RemoteEmployeeDataSource(employeeService)
    }

    @Singleton
    @Provides
    fun provdesWeatherCache(): EmployeeCache {
        return EmployeeMemoryCache()
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(
            employeeDataSource: EmployeeDataSource,
            employeeCache: EmployeeCache
    ): EmployeeRepository {
        return DefaultEmployeeRepository(employeeDataSource, employeeCache, Dispatchers.IO)
    }
}
