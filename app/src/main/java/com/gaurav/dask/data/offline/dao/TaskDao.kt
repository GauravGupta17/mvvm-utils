package com.gaurav.dask.data.offline.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gaurav.dask.data.model.table.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    fun deleteTask(id:Int)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

    @Query("UPDATE task SET isDone =:isDone WHERE id=:id")
    fun isTaskDone(id: Int,isDone:Boolean)
}