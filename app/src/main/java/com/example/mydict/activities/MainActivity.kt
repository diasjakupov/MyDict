package com.example.mydict.activities



import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast


import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar

import androidx.lifecycle.Observer


import com.example.mydict.DictApplication
import com.example.mydict.R


import com.example.mydict.fragments.CategoryRecyclerViewFR


import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider



class MainActivity : AppCompatActivity() {
    private val viewModel: CategoryViewModel by viewModels {
        CategoryViewModelProvider((application as DictApplication).repository)
    }
    private lateinit var toolbar: Toolbar
    private lateinit var searchInput: EditText
    private lateinit var clearText: MenuItem
    private lateinit var searchItem: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolBar()

        viewModel.categories.observe(this, Observer { category ->
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.flContainer, CategoryRecyclerViewFR.newInstance(
                        category
                    )
                )
                .commit()
        })

    }

    private fun setToolBar(){
        toolbar=findViewById(R.id.custom_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "My dictionary"
        searchInput=findViewById(R.id.search_edittext)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        clearText=menu!!.findItem(R.id.clear_text)
        searchItem=menu!!.findItem(R.id.action_search)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                supportActionBar?.title=""
                searchInput.visibility=VISIBLE
                searchItem.isVisible=false
                clearText.isVisible = true
            }
            R.id.clear_text -> {
                supportActionBar?.title = "My dictionary"
                searchInput.visibility=GONE
                clearText.isVisible = false
                searchItem.isVisible=true
            }
            else -> Toast.makeText(this, "Unknown click", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}