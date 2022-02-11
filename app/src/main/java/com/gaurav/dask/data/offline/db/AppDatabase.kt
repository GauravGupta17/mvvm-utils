package com.gaurav.dask.data.offline.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gaurav.dask.data.model.table.Category
import com.gaurav.dask.data.model.table.Task
import com.gaurav.dask.data.offline.dao.CategoryDao
import com.gaurav.dask.data.offline.dao.TaskDao

@Database(entities = [Task::class,Category::class], version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao():CategoryDao


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "Dask.db").fallbackToDestructiveMigration()
                .build()
        }
    }

}