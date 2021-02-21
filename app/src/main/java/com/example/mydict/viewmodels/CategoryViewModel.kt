package com.example.mydict.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydict.db.FakeDataBase
import com.example.mydict.models.Category

class CategoryViewModel: ViewModel() {
    private var categories:MutableLiveData<MutableList<Category>> =
            MutableLiveData()

    init{
        categories.value=FakeDataBase.categories
    }

    fun getCategories():LiveData<MutableList<Category>>{
        return categories
    }



}