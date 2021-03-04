package com.example.mydict.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydict.models.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getAllWords():Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE category=:id")
    fun getWordListById(id: Int): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word:Word)
}