package com.github.liangyu.employee.data.remote.network

import com.github.liangyu.employee.common.DataLoadingException
import com.github.liangyu.employee.common.Result.Error
import com.github.liangyu.employee.common.Result.Success
import com.github.liangyu.employee.data.DefaultEmployeeRepository
import com.github.liangyu.employee.data.EmployeeDataSource
import com.github.liangyu.employee.data.EmployeeRepository
import com.github.liangyu.employee.data.cache.EmployeeCache
import com.github.liangyu.employee.data.remote.network.response.EmployeeEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultEmployeeRepositoryTest {

    private lateinit var sut: EmployeeRepository
    private var dataSource: EmployeeDataSource = mockk()
    private var cacheTD = FakeCache()

    @Before
    fun setup() {
        sut = DefaultEmployeeRepository(
            dataSource,
            cacheTD,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `getEmployees success cache response wont call data source`() = runBlockingTest {
        //Arrange
        cacheTD.setEmployees(employeeList)
        //Act
        val result = sut.getEmployees()
        //Assert
        coVerify(exactly = 0) { dataSource.getEmployees() }
        (result as Success).data shouldEqual employeeList
    }

    @Test
    fun `getEmployees remote data should update cache`() = runBlockingTest {
        //Arrange
        cacheTD.setEmployees(emptyEmployeeList)
        coEvery { dataSource.getEmployees() } returns Success(employeeList)
        //Act
        sut.getEmployees()
        //Assert
        cacheTD.employeeListData shouldEqual employeeList
    }

    @Test
    fun `getEmployees failed cache response will get data from data source`() = runBlockingTest {
        //Arrange
        cacheTD.setEmployees(emptyEmployeeList)
        coEvery { dataSource.getEmployees() } returns Success(employeeList)
        //Act
        val result = sut.getEmployees()
        //Assert
        coVerify(exactly = 1) { dataSource.getEmployees() }
        (result as Success).data shouldEqual employeeList
    }

    @Test
    fun `getEmployee remote data source return Error`() = runBlockingTest {
        //Arrange
        coEvery { dataSource.getEmployees() } returns Error(Exception("Fake exception"))
        //Act
        val result = sut.getEmployees()
        //Assert
        (result as Error).exception shouldBeInstanceOf DataLoadingException::class
    }

    //----------------------------------Helper--------------------------------------
    private val employeeEntity =
        EmployeeEntity("biography", "email", "employeeType", "name", "", "", "", "", "")
    private val employeeList = listOf(employeeEntity)
    private val emptyEmployeeList = emptyList<EmployeeEntity>()

    private class FakeCache : EmployeeCache {
        var employeeListData = emptyList<EmployeeEntity>()

        override suspend fun setEmployees(employeeList: List<EmployeeEntity>) {
            employeeListData = employeeList
        }

        override suspend fun getEmployees() = employeeListData
    }
}
