package com.example.mydict.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.mydict.db.DictRepository
import com.example.mydict.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WordViewModel(private val repo:DictRepository): ViewModel() {
    private val words:LiveData<List<Word>> ? =null
    var searchText= MutableLiveData<String>()
    var searchWords:LiveData<List<Word>>? = null

    fun getWordsbyCategoryId(category: Int): LiveData<List<Word>>{
        return repo.getWordsByCategoryId(category).asLiveData()
    }

    fun getSearchText():LiveData<String>{
        Log.e("TESTING", "HEllo")
        return searchText
    }

    fun getWordBySearchText(text:String): LiveData<List<Word>>{
        var data=repo.getAllWords()
        viewModelScope.launch(Dispatchers.Default) {
            data=data.map { it.filter { word -> word.name.contains(text, true) } }
        }
        searchWords=data.asLiveData()
        return searchWords as LiveData<List<Word>>
    }

    fun clearSearchList(){
        searchWords=null
    }

    fun insertWord(name:String, category: Int, translate:String, example:String=""){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertWord(Word(name = name,
                translate = translate,
                example = example,
                category = category)   )
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
