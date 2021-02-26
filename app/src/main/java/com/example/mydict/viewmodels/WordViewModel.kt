package com.example.mydict.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.mydict.db.DictRepository
import com.example.mydict.models.Word

class WordViewModel(private val repo:DictRepository): ViewModel() {
    val words:LiveData<List<Word>> = repo.words.asLiveData()
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
