package com.example.mydict.activities


import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.mydict.R
import com.example.mydict.adapters.CategoryAdapter
import com.example.mydict.db.FakeDataBase
import com.example.mydict.models.Category


class MainActivity : AppCompatActivity() {
    lateinit var adapter : CategoryAdapter
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val width=Resources.getSystem().displayMetrics.widthPixels
        val rvCategory=findViewById<ListView>(R.id.rvCategory)
        adapter = CategoryAdapter(this,
                FakeDataBase.categories, width)
        rvCategory.adapter = adapter
    }
}