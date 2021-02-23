package com.example.mydict.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mydict.models.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {
    @Query("SELECT * FROM word_table WHERE category=:id")
    fun getWordListById(id: Int): Flow<List<Word>>
}