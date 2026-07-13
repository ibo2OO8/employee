package com.ibrohimapk3.employeelist.data

import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.mapper.toEmployee
import com.ibrohimapk3.employeelist.data.mapper.toEmployeeEntity
import com.ibrohimapk3.employeelist.data.remote.ApiService
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import android.util.Log
import com.ibrohimapk3.employeelist.presentation.model.Employee

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
        } catch (e: Exception) { }
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
                  //      Log.d("Firebase", FirebaseApp.getInstance().name)
                } catch (e: Exception) { }
            }
            .map { list ->
                list.map { it.toEmployee() }
            }
    }

    override suspend fun getAboutEmployeeById(id: String): Employee {
        return dao.getEmployeeById(id).first().toEmployee()
    }


}