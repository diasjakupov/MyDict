package com.example.mydict.db

import androidx.lifecycle.LiveData
import com.example.mydict.dao.CategoryDao
import com.example.mydict.dao.WordDao
import com.example.mydict.models.Category
import com.example.mydict.models.Word
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
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

    suspend fun updateWordProgress(id:Int, progress:Int){
        WordDao.updateWordProgress(progress, id)
    }

    suspend fun updateCategoryProgress(id:Int, progress:Int){
        CategoryDao.updateCategoryProgress(id, progress)
    }

    suspend fun UpdateCategoryName(id:Int, name:String){
        CategoryDao.updateCategoryName(id, name)
    }

    suspend fun deleteCategoryWithWords(category_id:Int){
        WordDao.deleteWordByCategory(category_id)
        CategoryDao.deleteCategory(category_id)
    }

    suspend fun insertCategory(category:Category){
        CategoryDao.insert(category)
    }

    suspend fun insertExample(examples: ArrayList<String>, id:Int){
        val jsonExamples=Gson().toJson(examples)
        WordDao.insertExample(jsonExamples, id)
    }

    suspend fun insertWord(name:String, category: Int, translate:String, example:String=""){
        val examples= arrayListOf<String>()
        if(example.isNotEmpty()){
            examples.add(example)
        }
        if(examples.size>0){
            val word=Word(name, translate, examples, 0, category)
            WordDao.insert(word)
        }
        val word=Word(name, translate, examples, 0, category)
        WordDao.insert(word)

    }

    suspend fun updateWord(name:String, translate: String, id:Int){
        WordDao.updateWord(name, translate, id)
    }

}
