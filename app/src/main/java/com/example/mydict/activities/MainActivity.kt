package com.example.mydict.activities




import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast


import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener

import androidx.lifecycle.Observer


import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.db.DictRepository


import com.example.mydict.fragments.CategoryRecyclerViewFR
import com.example.mydict.fragments.SearchListFragment


import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider
import com.example.mydict.viewmodels.WordViewModel
import com.example.mydict.viewmodels.WordViewModelProvider


class MainActivity : AppCompatActivity() {
    private val viewModel: CategoryViewModel by viewModels {
        CategoryViewModelProvider((application as DictApplication).repository)
    }

    private val wordViewModel: WordViewModel by viewModels{
        WordViewModelProvider((application as DictApplication).repository)
    }

    private lateinit var toolbar: Toolbar
    private lateinit var searchInput: EditText
    private lateinit var clearText: MenuItem
    private lateinit var searchItem: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        setRecyclerCategoryView()
    }


    private fun setRecyclerCategoryView(){
        viewModel.categories.observe(this, { category ->
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
        searchInput.addTextChangedListener {
            wordViewModel.searchText.value=it.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        clearText=menu!!.findItem(R.id.clear_text)
        searchItem= menu.findItem(R.id.action_search)
        return true
    }

    private fun onSearchClick(){
        supportActionBar?.title=""
        searchInput.visibility=VISIBLE
        searchItem.isVisible=false
        clearText.isVisible = true

        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, SearchListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun onCancelClick(){
        supportActionBar?.title = "My dictionary"
        searchInput.visibility=GONE
        searchInput.text.clear()
        clearText.isVisible = false
        searchItem.isVisible=true
        setRecyclerCategoryView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                onSearchClick()
            }
            R.id.clear_text -> {
                onCancelClick()
            }
            else -> Toast.makeText(this, "Unknown click", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}