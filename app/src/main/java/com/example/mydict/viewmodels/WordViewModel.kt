package com.example.mydict.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.mydict.db.DictRepository
import com.example.mydict.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WordViewModel(private val repo:DictRepository): ViewModel() {
    private val words:LiveData<List<Word>> ? =null
    var searchText= MutableLiveData<String>()
    var searchWords:LiveData<List<Word>>? = null
    var currentCategoryId =  MutableLiveData<Int>()

    fun getWordsbyCategoryId(category: Int): LiveData<List<Word>>{
        return repo.getWordsByCategoryId(category).asLiveData()
    }

    fun getSearchText():LiveData<String>{
        return searchText
    }

    private fun filterWordData(initialData: Flow<List<Word>>, text: String): LiveData<List<Word>> {
        var data=initialData

        data=data.map { it.filter { word ->
            word.name.contains(text, true)
        } }
        return data.asLiveData()
    }

    fun getWordBySearchText(text:String, category: Int? = 0): LiveData<List<Word>>{
        return if(category==0){
            var data=repo.getAllWords()
            var filteredData=filterWordData(data, text)
            searchWords=filteredData
            searchWords as LiveData<List<Word>>
        } else{
            var data= category?.let { repo.getWordsByCategoryId(it) }
            var filteredData= data?.let { filterWordData(it, text) }
            searchWords=filteredData
            searchWords as LiveData<List<Word>>
        }
    }

    fun insertWord(name:String, category: Int, translate:String, example:String=""){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertWord(name = name,
                translate = translate,
                example = example,
                category = category)
        }
    }
}

class WordViewModelProvider(private val repo:DictRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
