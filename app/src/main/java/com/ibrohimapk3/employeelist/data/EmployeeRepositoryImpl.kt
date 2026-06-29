package com.ibrohimapk3.employeelist.data

import android.util.Log
import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.mapper.toEmployee
import com.ibrohimapk3.employeelist.data.mapper.toEmployeeEntity
import com.ibrohimapk3.employeelist.data.remote.ApiService
import com.ibrohimapk3.employeelist.domain.entety.EmployeeDomain
import com.ibrohimapk3.employeelist.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class EmployeeRepositoryImpl(
    private val dao: EmployeeDao,
    private val api: ApiService
) : EmployeeRepository {
    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getEmployees(): Flow<List<EmployeeDomain>> {
        return dao.getAllItem()
            .onStart {
                val localData = dao.getAllItem().first()
                if (localData.isEmpty()) {
                    val remote = api.getEmployee()

                    dao.insertItems(
                        remote.results.map { it.toEmployeeEntity() }
                    )
                    Log.d("logInDao" , remote.results[0].toString())
                }
            }
            .map { list ->
                list.map { it.toEmployee() }
            }
    }

    override suspend fun getAboutEmployee(): EmployeeDomain {
        TODO("Not yet implemented")
    }
}



