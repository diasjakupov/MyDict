package com.example.mydict.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.EXTRA_CATEGORY
import com.example.mydict.R
import com.example.mydict.adapters.WordAdapter
import com.example.mydict.db.DictRepository
import com.example.mydict.db.FakeDataBase
import com.example.mydict.models.Category
import com.example.mydict.models.Word
import com.example.mydict.viewmodels.WordViewModel
import com.example.mydict.viewmodels.WordViewModelProvider


class WordListActivity : AppCompatActivity() {
    lateinit var wordadapter:WordAdapter

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelProvider((application as DictApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        var category=intent.getIntExtra(EXTRA_CATEGORY, 0)

        wordadapter=WordAdapter()
        val rvWords=findViewById<RecyclerView>(R.id.rvWords)
        rvWords.adapter=wordadapter
        rvWords.layoutManager=LinearLayoutManager(this)

        wordViewModel.getWords(category).observe(this, Observer {
            wordadapter.refreshData(it)
        })


    }
}