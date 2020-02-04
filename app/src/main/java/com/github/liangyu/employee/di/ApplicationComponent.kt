package com.github.liangyu.employee.di

import android.content.Context
import com.github.liangyu.employee.EmployeeApplication
import com.github.liangyu.employee.di.domain.DomainModule
import com.github.liangyu.employee.di.network.ServiceModule
import com.github.liangyu.employee.di.presentation.EmployeeModule
import com.github.liangyu.employee.di.repository.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ServiceModule::class,
            DataModule::class,
            DomainModule::class,
            EmployeeModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<EmployeeApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
