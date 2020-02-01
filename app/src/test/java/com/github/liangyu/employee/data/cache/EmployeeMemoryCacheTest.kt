package com.github.liangyu.employee.data.cache

import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class EmployeeMemoryCacheTest {

    private lateinit var sut: EmployeeCache

    @Before
    fun setup() {
        sut = EmployeeMemoryCache()
    }

    @Test
    fun `getEmployees before set should return empty list`() = runBlockingTest {
        //Arrange
        //Act
        val result = sut.getEmployees()
        //Assert
        result shouldEqual emptyList()
    }

    @Test
    fun `setEmployees(list) should set employee cache`() = runBlockingTest {
        //Arrange
        //Act
        sut.setEmployees(employeeList)
        val result = sut.getEmployees()
        //Assert
        result shouldEqual employeeList
    }

    @Test
    fun `setEmployees(list) should overwrite previous employee cache`() = runBlockingTest {
        //Arrange
        sut.setEmployees(employeeList)
        //Act
        sut.setEmployees(newEmployeeList)
        val result = sut.getEmployees()
        //Assert
        result shouldEqual newEmployeeList
    }

    //--------------------------Helper----------------------------
    val employeeEntity = EmployeeEntity("biography", "email", "employeeType", "name", "", "", "", "", "")
    val employeeList = listOf(employeeEntity)

    val newEmployeeEntity = EmployeeEntity("new biography", "email", "employeeType", "name", "", "", "", "", "")
    val newEmployeeList = listOf(newEmployeeEntity)
}
