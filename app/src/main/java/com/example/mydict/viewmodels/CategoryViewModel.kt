package com.example.mydict.viewmodels



import android.util.Log
import androidx.lifecycle.*
import com.example.mydict.db.DictRepository

import com.example.mydict.models.Category
import kotlinx.coroutines.launch

class CategoryViewModel(private val repo: DictRepository): ViewModel() {
    var categories: LiveData<List<Category>> = repo.categories.asLiveData()

    fun insertCategory(category: Category){
        viewModelScope.launch {
            repo.insertCategory(category)
        }
    }

}

class CategoryViewModelProvider(private val repo:DictRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}