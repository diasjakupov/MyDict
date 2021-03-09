package com.example.mydict.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mydict.db.DictRepository
import com.example.mydict.models.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.FieldPosition

class WordDetailViewModel(private val repo: DictRepository): ViewModel() {
    var word: LiveData<Word>? = null

    fun getWord(id:Int){
        word=repo.getWord(id)
    }

    fun updateWord(name:String, translate:String, id:Int){
        viewModelScope.launch(Dispatchers.Default){
            repo.updateWord(name, translate, id)
        }
    }

    fun insertExamples(example: String, id:Int){
        var examples=word?.value!!.example ?: arrayListOf()
        examples.add(example.replace(" ", "_"))
        viewModelScope.launch(Dispatchers.Default){
            repo.insertExample(examples.toString(), id)
        }
    }

    fun deleteExample(position:Int, id:Int){
        var examples=word?.value!!.example ?: arrayListOf()
        if(examples.size>0){
            examples.removeAt(position)
        }
        Log.e("TESTING INSERT", "$examples at $id")
        viewModelScope.launch(Dispatchers.Default){
            repo.insertExample(examples.toString(), id)
        }
    }

    fun deleteWord(id:Int){
        viewModelScope.launch(Dispatchers.Default) {
            repo.deleteWord(id)
        }
    }
}

class WordDetailViewModelProvider(private val repo: DictRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordDetailViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}