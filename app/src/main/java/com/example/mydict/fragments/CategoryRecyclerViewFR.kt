package com.example.mydict.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.adapters.RecyclerCategoryAdapter
import com.example.mydict.models.Category
import com.example.mydict.models.CategoryAdapter
import com.example.mydict.viewmodels.WordViewModel
import com.example.mydict.viewmodels.WordViewModelProvider


class CategoryRecyclerViewFR(var categoryList: List<Category>) : Fragment() {
    private lateinit var adapter: RecyclerCategoryAdapter
    lateinit var rvCategory:RecyclerView
    private val wordViewModel: WordViewModel by activityViewModels{
        WordViewModelProvider((requireActivity().application as DictApplication).repository)
    }


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

        adapter = RecyclerCategoryAdapter(
            CategoryAdapter.MainCategoryAdapter,
            {category->onItemClick(category)},
            {category ->onLongItemClick(category)  }
        )

        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(activity)
        rvCategory.setHasFixedSize(true)

        adapter.refreshDataList(categoryList)


    }

    private fun onLongItemClick(category: Category):Boolean{
        val dialog=DeleteOrUpdateCategory.newInstance(category)
        dialog.show(requireActivity().supportFragmentManager, "DELETE CATEGORY")
        return true
    }

    private fun onItemClick(category: Category){
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.flContainer, WordListFragment.newInstance(category = category.id)
        ).addToBackStack(null).commit()
        (activity as AppCompatActivity).supportActionBar?.title=category.title
    }

    override fun onStart() {
        super.onStart()
        wordViewModel.currentCategoryId.value=0
        (activity as AppCompatActivity).supportActionBar?.title="My dictionary"
    }

    companion object {

        @JvmStatic
        fun newInstance(categoryList:List<Category>) =
            CategoryRecyclerViewFR(categoryList)
    }

}