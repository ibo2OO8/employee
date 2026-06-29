package com.ibrohimapk3.employeelist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrohimapk3.employeelist.data.local.entity.EmployeeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(list: List<EmployeeEntity>)


    @Query("SELECT * FROM EmployeesList")
    fun getAllItem(): Flow<List<EmployeeEntity>>

}