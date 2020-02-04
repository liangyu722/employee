package com.github.liangyu.employee.di.domain

import com.github.liangyu.employee.data.EmployeeRepository
import com.github.liangyu.employee.domain.ViewEmployees
import com.github.liangyu.employee.domain.ViewEmployeesUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun providesViewWeatherUseCase(repository: EmployeeRepository): ViewEmployees {
        return ViewEmployeesUseCase(repository)
    }
}
