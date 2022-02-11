package com.gaurav.dask.data.model.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "date") val date: String? = null,
    @ColumnInfo(name = "list_id") val listId: Int? = null,
    @ColumnInfo(name = "isSynced") val isSynced:Boolean=false,
    @ColumnInfo(name = "isDone") val isDone:Boolean=false
)

@Entity(tableName = "category_list")
data class Category(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "isSynced") val isSynced:Boolean=false
    )
