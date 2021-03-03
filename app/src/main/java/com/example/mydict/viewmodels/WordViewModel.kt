package com.example.mydict.viewmodels

import androidx.lifecycle.*
import com.example.mydict.db.DictRepository
import com.example.mydict.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(private val repo:DictRepository): ViewModel() {
    private val words:LiveData<List<Word>> ? =null

    fun getWords(category: Int): LiveData<List<Word>>{
        return repo.getWords(category).asLiveData()
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
