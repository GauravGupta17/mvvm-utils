package com.gaurav.dask.data.offline.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gaurav.dask.data.model.table.Category

@Dao
interface CategoryDao {

@Insert
fun insertCategory(category: Category)

@Query("SELECT * FROM category_list")
fun getAllCategory():List<Category>

@Delete
fun deleteCategory(category: Category)


}