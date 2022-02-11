package com.gaurav.dask.ui.task

import androidx.lifecycle.ViewModel
import com.gaurav.dask.data.model.table.Task
import kotlinx.coroutines.flow.Flow


class TaskVM(private val taskRepo: TaskRepo):ViewModel() {

suspend fun getAllTask(): Flow<List<Task>> = taskRepo.getAllTask()

suspend fun addTask(task: Task) =taskRepo.insertTask(task)


}