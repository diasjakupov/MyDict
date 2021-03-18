package com.example.mydict.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.adapters.WordExampleAdapter
import com.example.mydict.models.StatusModelChange
import com.example.mydict.viewmodels.WordDetailViewModel
import com.example.mydict.viewmodels.WordDetailViewModelProvider


class WordDetailFragment(var word: Int) : Fragment() {
    private lateinit var rvExamples: RecyclerView
    private lateinit var wordName: EditText
    private lateinit var wordTranslate: EditText
    private val wordDetailViewModel: WordDetailViewModel by activityViewModels {
        WordDetailViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private lateinit var closeDetail: TextView
    private lateinit var addExample: ImageButton
    private lateinit var updateCategory:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wordDetailViewModel.getWord(word)
        return inflater.inflate(R.layout.fragment_word_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateCategory = view.findViewById(R.id.updateWordDetail)
        wordName = view.findViewById(R.id.word_detail_name)
        wordTranslate = view.findViewById(R.id.word_detail_translate)
        closeDetail = view.findViewById(R.id.closeDetail)
        addExample = view.findViewById(R.id.addExample)

        addExample.setOnClickListener {
            val dialog = AddBottomWindow.newInstance(word, StatusModelChange.CREATE_EXAMPLE)
            dialog.show(childFragmentManager, "ADD_EXAMPLE")
        }

        closeDetail.setOnClickListener {
            wordDetailViewModel.deleteWord(word)
            activity?.onBackPressed()
        }

        wordName.addTextChangedListener {
            wordDetailViewModel.word?.value!!.name = it.toString()
        }

        wordTranslate.addTextChangedListener {
            wordDetailViewModel.word?.value!!.translate = it.toString()
        }

        wordDetailViewModel.word?.observe(viewLifecycleOwner, {
            wordName.setText(it.name)
            wordTranslate.setText(it.translate)
        })

        updateCategory.setOnClickListener { changeWord() }


        rvExamples = view.findViewById(R.id.rvExamples)
        initializeRecyclerView()
    }


    private fun changeWord() {
        wordDetailViewModel.updateWord(
            wordDetailViewModel.word?.value!!.name,
            wordDetailViewModel.word?.value!!.translate,
            word
        )
    }

    private fun initializeRecyclerView() {
        val adapter = WordExampleAdapter({ _, position ->
            wordDetailViewModel.deleteExample(position, word)
        }, { example, position ->
            val dialog = AddBottomWindow.newInstance(
                word,
                StatusModelChange.UPDATE_EXAMPLE,
                position,
                example
            )
            dialog.show(childFragmentManager, "EDIT_EXAMPLE")
        })
        rvExamples.adapter = adapter
        rvExamples.layoutManager = LinearLayoutManager(activity)

        wordDetailViewModel.word?.observe(viewLifecycleOwner, {
            it.example.let { it1 -> adapter.refreshData(it1) }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(word: Int) =
            WordDetailFragment(word)
    }
}