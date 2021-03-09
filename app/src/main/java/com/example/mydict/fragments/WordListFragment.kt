package com.example.mydict.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.adapters.WordAdapter
import com.example.mydict.viewmodels.WordViewModel
import com.example.mydict.viewmodels.WordViewModelProvider


class WordListFragment(var category: Int) : Fragment() {
    lateinit var wordadapter:WordAdapter

    private val wordViewModel: WordViewModel by activityViewModels {
        WordViewModelProvider((requireActivity().application as DictApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_word_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordadapter=WordAdapter(activity = (activity as AppCompatActivity))
        val rvWords=view.findViewById<RecyclerView>(R.id.rvWords)
        rvWords.adapter=wordadapter
        rvWords.layoutManager=LinearLayoutManager(activity)

        wordViewModel.getWordsbyCategoryId(category).observe(viewLifecycleOwner, {
            wordadapter.refreshData(it)
        })


    }

    override fun onStart() {
        super.onStart()
        wordViewModel.currentCategoryId.value=category
    }


    companion object {
        @JvmStatic
        fun newInstance(category: Int) =
            WordListFragment(category)
    }
}