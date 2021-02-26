package com.example.mydict.db

import android.util.Log
import com.example.mydict.dao.CategoryDao
import com.example.mydict.dao.WordDao
import com.example.mydict.models.Category
import com.example.mydict.models.Word
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DictRepository(private val WordDao:WordDao, private val CategoryDao:CategoryDao) {
    public var words: Flow<List<Word>> = WordDao.getWordListById(1)
    public var categories: Flow<List<Category>> = CategoryDao.getCategoryList()

    suspend fun insertCategory(category:Category){
        CategoryDao.insert(category)
    }
}