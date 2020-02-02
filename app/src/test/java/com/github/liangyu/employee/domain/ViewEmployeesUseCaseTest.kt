package com.github.liangyu.employee.domain

import com.github.liangyu.employee.common.Result
import com.github.liangyu.employee.common.Result.*
import com.github.liangyu.employee.data.EmployeeRepository
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity
import com.github.liangyu.employee.model.Employee
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class ViewEmployeesUseCaseTest {

    private lateinit var sut: ViewEmployees
    private val repository: EmployeeRepository = mockk()

    @Before
    fun setup() {
        sut = ViewEmployeesUseCase(repository)
    }

    @Test
    fun `getEmployees repository return empty list`() = runBlockingTest {
        //Arrange
        coEvery { repository.getEmployees() } returns emptyRepositoryResponse
        //Act
        val result = sut.getEmployees()
        //Assign
        result shouldEqual emptyResponse
    }

    @Test
    fun `getEmployees repository return list of employee entities`() = runBlockingTest {
        //Arrange
        coEvery { repository.getEmployees() } returns employeeListRepositoryResponse
        //Act
        val result = sut.getEmployees()
        //Assign
        result shouldEqual employeeListResponse
    }

    @Test
    fun `getEmployees repository return Error`() = runBlockingTest {
        //Arrange
        coEvery { repository.getEmployees() } returns exceptionResponse
        //Act
        val result = sut.getEmployees()
        //Assign
        result shouldEqual exceptionResponse
    }

    //------------------------------Helper----------------------------------------------
    private val emptyRepositoryResponse = Success(emptyList<EmployeeEntity>())
    private val emptyResponse = Success(emptyList<Employee>())
    private val employeeEntity =
        EmployeeEntity("biography", "email", "employeeType", "name", "", "", "", "", "")
    private val employeeList = listOf(employeeEntity)
    private val employeeListRepositoryResponse = Success(employeeList)
    private val employee = Employee(
        employeeEntity.uuid,
        employeeEntity.fullName,
        employeeEntity.phoneNumber,
        employeeEntity.emailAddress,
        employeeEntity.photoUrlSmall,
        employeeEntity.team
    )
    private val employeeListResponse = Success(listOf(employee))
    private val exceptionResponse = Error(Exception("fake exception"))
}
