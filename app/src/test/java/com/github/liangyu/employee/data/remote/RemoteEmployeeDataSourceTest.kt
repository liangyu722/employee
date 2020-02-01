package com.github.liangyu.employee.data.remote

import com.github.liangyu.employee.common.Result.Error
import com.github.liangyu.employee.common.Result.Success
import com.github.liangyu.employee.data.EmployeeDataSource
import com.github.liangyu.employee.data.remote.network.EmployeeService
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity
import com.github.liangyu.employee.data.remote.network.response.EmployeeResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteEmployeeDataSourceTest {

    private lateinit var sut: EmployeeDataSource
    private val employeeService: EmployeeService = mockk()

    @Before
    fun setup() {
        sut = RemoteEmployeeDataSource(employeeService)
    }

    @Test
    fun `getEmployees() employeeService empty response`() = runBlockingTest {
        //Arrange
        coEvery { employeeService.getEmployeesAsync() } returns emptyEmployeeResponse
        //Act
        val result = sut.getEmployees()
        //Assert
        result shouldBeInstanceOf Success::class
        (result as Success).data shouldEqual emptyList()
    }

    @Test
    fun `getEmployees() employeeService employee list response`() = runBlockingTest {
        //Arrange
        coEvery { employeeService.getEmployeesAsync() } returns employeeResponse
        //Act
        val result = sut.getEmployees()
        //Assert
        result shouldBeInstanceOf Success::class
        (result as Success).data shouldEqual employeeResponse
    }

    @Test
    fun `getEmployees() employeeService exception response`() = runBlockingTest {
        //Arrange
        coEvery { employeeService.getEmployeesAsync() } throws employeeServiceException
        //Act
        val result = sut.getEmployees()
        //Assert
        result shouldBeInstanceOf Error::class
        (result as Error).exception shouldEqual employeeServiceException
    }

    //--------------------Helper---------------------------------
    private val emptyEmployeeResponse = EmployeeResponse(emptyList())

    private val employeeEntity =
        EmployeeEntity("biography", "email", "employeeType", "name", "", "", "", "", "")
    private val employeeList = listOf(employeeEntity)
    private val employeeResponse = EmployeeResponse(employeeList)

    private val employeeServiceException = Exception("Mock exception")
}