package com.example.mydict.db

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mydict.dao.CategoryDao
import com.example.mydict.dao.WordDao
import com.example.mydict.models.Category
import com.example.mydict.models.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class DictRepository(private val WordDao:WordDao, private val CategoryDao:CategoryDao) {
    public var categories: Flow<List<Category>>  = CategoryDao.getCategoryList()



    fun getWords(category:Int): Flow<List<Word>>{
        return WordDao.getWordListById(category)
    }

    suspend fun insertCategory(category:Category){
        CategoryDao.insert(category)
    }

    suspend fun insertWord(word:Word){
        WordDao.insert(word)
    }
}