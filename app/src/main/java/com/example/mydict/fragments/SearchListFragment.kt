package com.example.mydict.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.adapters.WordAdapter
import com.example.mydict.models.Word
import com.example.mydict.viewmodels.WordViewModel
import com.example.mydict.viewmodels.WordViewModelProvider


class SearchListFragment : Fragment() {
    private val wordViewModel: WordViewModel by activityViewModels {
        WordViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private lateinit var adapter: WordAdapter
    private lateinit var rvWordList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvWordList=view.findViewById(R.id.searchList)
        adapter= WordAdapter()
        rvWordList.adapter=adapter
        rvWordList.layoutManager=LinearLayoutManager(activity)

        wordViewModel.getSearchText().observe(viewLifecycleOwner, { text->
            wordViewModel.getWordBySearchText(text).observe(viewLifecycleOwner, {list->
                adapter.refreshData(list)
            })
        })

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            SearchListFragment()
    }
}