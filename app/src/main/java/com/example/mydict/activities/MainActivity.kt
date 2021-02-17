package com.example.mydict.activities



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.EXTRA_CATEGORY

import com.example.mydict.R
import com.example.mydict.adapters.RecyclerCategoryAdapter
import com.example.mydict.db.FakeDataBase



class MainActivity : AppCompatActivity() {
    lateinit var adapter : RecyclerCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCategory=findViewById<RecyclerView>(R.id.rvCategory)
        adapter = RecyclerCategoryAdapter(this,
                FakeDataBase.categories){category ->
            val intent=Intent(this, WordListActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY, category)
            }
            startActivity(intent)
        }
        rvCategory.adapter = adapter
        rvCategory.layoutManager=LinearLayoutManager(this)
        rvCategory.setHasFixedSize(true)
    }
}