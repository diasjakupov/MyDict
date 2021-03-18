package com.example.mydict.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mydict.models.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getAllWords():Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE category=:id")
    fun getWordListById(id: Int): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE id=:id")
    fun getWord(id:Int): LiveData<Word>

    @Query("DELETE FROM word_table WHERE id=:id")
    suspend fun deleteWord(id:Int)

    @Query("DELETE FROM word_table WHERE category=:cat_id")
    suspend fun deleteWordByCategory(cat_id:Int)

    @Query("UPDATE word_table SET name=:name, translate=:translate WHERE id=:id")
    suspend fun updateWord(name:String,translate:String, id: Int)

    @Query("UPDATE word_table SET progress=:progress WHERE id=:id")
    suspend fun updateWordProgress(progress:Int, id:Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word:Word)

    @Query("UPDATE word_table SET example=:examples WHERE id=:id")
    suspend fun insertExample(examples:String, id:Int)
}