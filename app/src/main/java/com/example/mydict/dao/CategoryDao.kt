package com.example.mydict.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydict.models.Category
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table")
    fun getCategoryList(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category:Category)

    @Query("DELETE FROM category_table WHERE id=:id")
    suspend fun deleteCategory(id:Int)

    @Query("UPDATE category_table SET progress=:progress WHERE id=:id")
    suspend fun updateCategoryProgress(id:Int, progress:Int)

    @Query("UPDATE category_table SET title=:name WHERE id=:id")
    suspend fun updateCategoryName(id:Int, name:String)
}