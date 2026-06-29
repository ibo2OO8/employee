package com.ibrohimapk3.employeelist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibrohimapk3.employeelist.data.local.dao.EmployeeDao
import com.ibrohimapk3.employeelist.data.local.entity.EmployeeEntity


@Database(entities = [EmployeeEntity::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): EmployeeDao
    companion object {
        @Volatile
        private var db: MainDb? = null
        fun getDb(context: Context): MainDb {
            return db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDb::class.java,
                    "EmployeesList"
                )
                    .build()
                db = instance
                instance
            }
        }
    }
}