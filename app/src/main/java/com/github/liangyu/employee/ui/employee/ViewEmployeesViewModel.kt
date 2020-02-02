package com.github.liangyu.employee.ui.employee

import androidx.lifecycle.LiveData
import com.github.liangyu.employee.common.Event
import com.github.liangyu.employee.model.Employee

interface ViewEmployeesViewModel {

    val loading: LiveData<Boolean>
    val errorMessage: LiveData<Event<Int>>
    val employees: LiveData<List<Employee>>

}