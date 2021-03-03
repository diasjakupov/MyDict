package com.example.mydict.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.EXTRA_CATEGORY
import com.example.mydict.R
import com.example.mydict.activities.WordListActivity
import com.example.mydict.adapters.RecyclerCategoryAdapter
import com.example.mydict.models.Category
import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class CategoryRecyclerViewFR(var categoryList: List<Category>) : Fragment() {
    private lateinit var adapter: RecyclerCategoryAdapter
    lateinit var rvCategory:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_recycler_view_f_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCategory=view.findViewById(R.id.rvCategory)

        adapter = RecyclerCategoryAdapter() { category ->
            val intent = Intent(activity, WordListActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY, category?.id)
            }
            startActivity(intent)
        }

        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(activity)
        rvCategory.setHasFixedSize(true)

        adapter.refreshDataList(categoryList)


    }

    companion object {

        @JvmStatic
        fun newInstance(categoryList:List<Category>) =
            CategoryRecyclerViewFR(categoryList)
    }
}