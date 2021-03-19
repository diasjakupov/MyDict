package com.example.mydict.viewmodels

import androidx.lifecycle.*
import com.example.mydict.db.DictRepository
import com.example.mydict.models.Category
import com.example.mydict.models.UpdateWordStatus
import com.example.mydict.models.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class GameViewModel(var repo:DictRepository) : ViewModel() {
    var categoryList:LiveData<List<Category>> = repo.categories.asLiveData()
    var isChecked=MutableLiveData<Boolean>(false)
    var count=MutableLiveData<Int>()
    var knownWords=MutableLiveData<Int>(0)
    init {
        count.value=0
    }

    fun words(id:Int) = repo.getWordsByCategoryId(id).asLiveData()

    fun increaseCount(){
        count.value=count.value?.plus(1)
        isChecked.value=false
    }

    private fun updateCategoryProgress(category:Int){
        viewModelScope.launch {
            repo.getWordsByCategoryId(category).collect { words->
                val progresses=words.map { it.progress }
                val finalProgress=progresses.sum()/progresses.size
                repo.updateCategoryProgress(category, finalProgress)
            }
        }
    }


    fun updateWordProgress(word: Word, status:UpdateWordStatus, category:Int){
        if (status==UpdateWordStatus.OK){
            if(word.progress<91){
                viewModelScope.launch(Dispatchers.IO){
                    repo.updateWordProgress(word.id, word.progress+10)
                    updateCategoryProgress(category)
                }
            }else{
                viewModelScope.launch(Dispatchers.IO){
                    var progress=(100-word.progress)+word.progress
                    repo.updateWordProgress(word.id, progress)
                    updateCategoryProgress(category)
                }
            }

        }else{
            if(word.progress>=5){
                val finalProgress=word.progress-5
                viewModelScope.launch(Dispatchers.IO){
                    repo.updateWordProgress(word.id, finalProgress)
                    updateCategoryProgress(category)
                }
            }
        }

    }
}

class GameViewModelProvider(private val repo: DictRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}