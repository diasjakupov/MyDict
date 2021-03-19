package com.example.mydict.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.adapters.RecyclerCategoryAdapter
import com.example.mydict.models.Category
import com.example.mydict.models.CategoryAdapter
import com.example.mydict.viewmodels.GameViewModel
import com.example.mydict.viewmodels.GameViewModelProvider
import com.example.mydict.viewmodels.WordDetailViewModel
import com.example.mydict.viewmodels.WordDetailViewModelProvider

class ChooseCategory : Fragment() {
    private val viewModel: GameViewModel by activityViewModels{
        GameViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private lateinit var adapter:RecyclerCategoryAdapter
    private lateinit var rvChooseCategory:RecyclerView

    companion object {
        fun newInstance() = ChooseCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.choose_category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= RecyclerCategoryAdapter(CategoryAdapter.ChooseCategory){
            viewModel.words(it.id).observe(viewLifecycleOwner, {list->
                if(list.size <2){
                    Toast.makeText(activity, "The category should have more than 2 words", Toast.LENGTH_SHORT).show()
                }else{
                    (activity as AppCompatActivity)
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.flGame, GameFragment.newInstance(it))
                        .commit()
                }
            })


        }
        viewModel.categoryList.observe(viewLifecycleOwner, {
            adapter.refreshDataList(it)
        })

        rvChooseCategory=view.findViewById(R.id.chooseCategory)
        rvChooseCategory.adapter=adapter
        rvChooseCategory.layoutManager=LinearLayoutManager(activity)
    }

}