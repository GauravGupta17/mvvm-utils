package com.gaurav.dask.ui.task

import com.gaurav.dask.core.network.BaseRepo
import com.gaurav.dask.data.model.table.Task
import com.gaurav.dask.data.offline.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class TaskRepo(private val appDatabase: AppDatabase):BaseRepo() {

    fun getAllTask()= appDatabase.taskDao().getAllTask()
    suspend fun insertTask(task: Task)= withContext(Dispatchers.IO){appDatabase.taskDao().insertTask(task)}
    suspend fun updateTask(task: Task)=appDatabase.taskDao().updateTask(task)

}