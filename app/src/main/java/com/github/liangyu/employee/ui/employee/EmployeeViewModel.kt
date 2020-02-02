package com.github.liangyu.employee.ui.employee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.liangyu.employee.R
import com.github.liangyu.employee.common.Event
import com.github.liangyu.employee.common.Result.Error
import com.github.liangyu.employee.common.Result.Success
import com.github.liangyu.employee.domain.ViewEmployees
import com.github.liangyu.employee.model.Employee
import kotlinx.coroutines.launch

class EmployeeViewModel(
    private val useCase: ViewEmployees
) : ViewModel(), ViewEmployeesViewModel {

    override val loading = MutableLiveData<Boolean>()
    override val errorMessage = MutableLiveData<Event<Int>>()
    override val employees = MutableLiveData<List<Employee>>()

    init {
        load()
    }

    private fun load() {
        loading.value = true
        viewModelScope.launch {
            when (val result = useCase.getEmployees()) {
                is Success -> employees.value = result.data
                is Error -> setErrorMessage(R.string.employees_error)
            }
            loading.value = false
        }
    }

    private fun setErrorMessage(message: Int) {
        errorMessage.value = Event(message)
    }
}