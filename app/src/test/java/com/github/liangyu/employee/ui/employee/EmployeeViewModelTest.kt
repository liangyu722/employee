package com.github.liangyu.employee.ui.employee

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.liangyu.employee.MainCoroutineRule
import com.github.liangyu.employee.R
import com.github.liangyu.employee.common.Result.Error
import com.github.liangyu.employee.common.Result.Success
import com.github.liangyu.employee.domain.ViewEmployees
import com.github.liangyu.employee.model.Employee
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EmployeeViewModelTest {

    private lateinit var sut: ViewEmployeesViewModel
    private val useCase: ViewEmployees = mockk()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        coEvery { useCase.getEmployees() } returns emptyResponse
    }

    @Test
    fun `loading indicator is true when starting view model`() {
        //Arrange
        mainCoroutineRule.pauseDispatcher()
        //Act
        sut = EmployeeViewModel(useCase)
        //Assert
        sut.loading.value shouldEqual true
        mainCoroutineRule.resumeDispatcher()
    }

    @Test
    fun `loading indicator is false when finished loading`() {
        //Arrange
        //Act
        sut = EmployeeViewModel(useCase)
        //Assert
        sut.loading.value shouldEqual false
    }

    @Test
    fun `error message is set when use case return error`() {
        //Arrange
        coEvery { useCase.getEmployees() } returns exceptionResponse
        //Act
        sut = EmployeeViewModel(useCase)
        //Assert
        sut.loading.value shouldEqual false
        sut.errorMessage.value!!.peekContent() shouldEqual R.string.employees_error
    }

    @Test
    fun `employees is set if use case respond with empty response`() {
        //Arrange
        //Act
        sut = EmployeeViewModel(useCase)
        //Assert
        sut.empty.value shouldEqual true
        sut.employees.value shouldEqual emptyResponse.data
    }

    @Test
    fun `employees is set if use case respond with response`() {
        //Arrange
        coEvery { useCase.getEmployees() } returns employeeListResponse
        //Act
        sut = EmployeeViewModel(useCase)
        //Assert
        sut.empty.value shouldEqual false
        sut.employees.value shouldEqual employeeListResponse.data
    }

    //-----------------------Helper----------------------------------------
    private val emptyResponse = Success(emptyList<Employee>())
    private val employee = Employee(
        "uuid",
        "biography",
        "full name",
        "number",
        "",
        ","
    )
    private val employeeListResponse = Success(listOf(employee))
    private val exceptionResponse = Error(Exception("fake exception"))
}