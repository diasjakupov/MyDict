package com.example.mydict.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.EXTRA_CATEGORY
import com.example.mydict.R
import com.example.mydict.adapters.WordAdapter
import com.example.mydict.db.FakeDataBase
import com.example.mydict.models.Category


class WordListActivity : AppCompatActivity() {
    lateinit var wordadapter:WordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        var category=intent.getIntExtra(EXTRA_CATEGORY, 0)
        var wordList=FakeDataBase.words.filter { it.category == category}
        val rvWords=findViewById<RecyclerView>(R.id.rvWords)
        rvWords.adapter=WordAdapter(this, wordList)
        rvWords.layoutManager=LinearLayoutManager(this)
    }
}