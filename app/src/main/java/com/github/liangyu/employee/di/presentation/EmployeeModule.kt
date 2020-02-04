package com.github.liangyu.employee.di.presentation

import androidx.lifecycle.ViewModel
import com.github.liangyu.employee.di.ViewModelBuilder
import com.github.liangyu.employee.di.ViewModelKey
import com.github.liangyu.employee.ui.employee.EmployeesActivity
import com.github.liangyu.employee.ui.employee.EmployeesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class EmployeeModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    abstract fun employeeActivity(): EmployeesActivity

    @Binds
    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    abstract fun bindViewModel(viewmodel: EmployeesViewModel): ViewModel

}
