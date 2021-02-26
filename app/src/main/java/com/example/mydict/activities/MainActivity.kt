package com.example.mydict.activities


import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.EXTRA_CATEGORY

import com.example.mydict.R
import com.example.mydict.adapters.RecyclerCategoryAdapter
import com.example.mydict.models.Category

import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider



class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerCategoryAdapter
    private val viewModel: CategoryViewModel by viewModels {
        CategoryViewModelProvider((application as DictApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCategory = findViewById<RecyclerView>(R.id.rvCategory)

        adapter = RecyclerCategoryAdapter() { category ->
            val intent = Intent(this, WordListActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY, category?.id)
            }
            startActivity(intent)
        }


        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(this)
        rvCategory.setHasFixedSize(true)

        viewModel.categories.observe(this, Observer {
            Log.e("TEST", "$it")
            adapter.refreshDataList(it as MutableList<Category>)
        })

    }

}