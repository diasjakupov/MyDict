package com.example.mydict.db

import android.util.Log
import androidx.lifecycle.LiveData
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
    var categories: Flow<List<Category>>  = CategoryDao.getCategoryList()


    fun getWordsByCategoryId(category:Int): Flow<List<Word>>{
        return WordDao.getWordListById(category)
    }

    fun getAllWords():Flow<List<Word>>{
        return WordDao.getAllWords()
    }

    fun getWord(id: Int):LiveData<Word>{
        return WordDao.getWord(id)
    }

    suspend fun deleteWord(word:Int){
        WordDao.deleteWord(word)
    }

    suspend fun insertCategory(category:Category){
        CategoryDao.insert(category)
    }

    suspend fun insertExample(examples: String, id:Int){
        WordDao.insertExample(examples, id)
    }


    suspend fun insertWord(name:String, category: Int, translate:String, example:String=""){
        var examples= arrayListOf<String>()
        if(example.isNotEmpty()){
            examples.add(example)
        }
        if(examples.size>0){
            var word=Word(name, translate, examples, 0, category)
            WordDao.insert(word)
        }
        var word=Word(name, translate, null, 0, category)
        WordDao.insert(word)

    }

    suspend fun updateWord(name:String, translate: String, id:Int){
        WordDao.updateWord(name, translate, id)
    }

}
