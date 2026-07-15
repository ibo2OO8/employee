package com.ibrohimapk3.employeelist.data.repositoryImpl

import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.mapper.toEmployee
import com.ibrohimapk3.employeelist.data.mapper.toEmployeeEntity
import com.ibrohimapk3.employeelist.data.remote.ApiService
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import com.ibrohimapk3.employeelist.domain.error.AppException
import com.ibrohimapk3.employeelist.presentation.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
class EmployeeRepositoryImpl(
    private val dao: EmployeeDao,
    private val api: ApiService
) : EmployeeRepository {
    override suspend fun refresh() {
        try {
            val remote = api.getEmployee()
            if (remote.results.isNotEmpty()) {
                dao.deleteAllData()
                dao.insertItems(remote.results.map { it.toEmployeeEntity() })
            }
        } catch (e: Exception) {
            throw AppException.NetworkException()
        }
    }

    override fun getEmployees(): Flow<List<Employee>> {
        return dao.getAllItem()
            .onStart {
                try {
                    val localData = dao.getAllItem().first()
                    if (localData.isEmpty()) {
                        val remote = api.getEmployee()
                        dao.insertItems(
                            remote.results.map { it.toEmployeeEntity() }
                        )
                    }
                } catch (e: Exception) {
                    throw AppException.NetworkException()
                }
            }
            .map { list ->
                list.map { it.toEmployee() }
            }
    }

    override suspend fun getAboutEmployeeById(id: String): Employee {
        return dao.getEmployeeById(id).first().toEmployee()
    }
}