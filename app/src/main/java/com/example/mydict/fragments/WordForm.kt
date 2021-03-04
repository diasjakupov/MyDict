package com.example.mydict.fragments


import android.content.Context
import android.content.res.Resources
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.models.Category
import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider
import com.example.mydict.viewmodels.WordViewModel
import com.example.mydict.viewmodels.WordViewModelProvider

class WordForm : DialogFragmentInstance() {
    private lateinit var createNewWord:TextView
    private lateinit var wordInput:TextView
    private lateinit var wordTranslate:TextView
    private lateinit var spinner: Spinner
    private val CategoryviewModel: CategoryViewModel by activityViewModels {
        CategoryViewModelProvider((activity?.application as DictApplication).repository)
    }
    private val WordviewModel:WordViewModel by activityViewModels{
        WordViewModelProvider((activity?.application as DictApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.fragment_word_form, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardView=view.findViewById<CardView>(R.id.WordcardView)
        getCardViewWeight()

        spinner=view.findViewById(R.id.spinner)
        createNewWord=view.findViewById(R.id.addWord)
        wordInput=view.findViewById(R.id.word)
        wordTranslate=view.findViewById(R.id.wordTranslate)

        val adapter=ArrayAdapter(
            requireActivity().baseContext,
            R.layout.spinner_text,
            arrayListOf<Category>()
        )

        CategoryviewModel.categories.observe(this, {
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        })


        spinner.adapter=adapter

        createNewWord.setOnClickListener {
            val cat=(spinner.selectedItem as Category)

            if(wordInput.text.toString().isNotEmpty() &&
                wordTranslate.text.toString().isNotEmpty()
                ) {

                WordviewModel.insertWord(
                    name = wordInput.text.toString(),
                    translate = wordTranslate.text.toString(),
                    category = cat.id
                )

                dialog?.dismiss()
            }else{
                Toast.makeText(activity,
                        "Please fill up the inputs",
                        Toast.LENGTH_SHORT).show()
            }

        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = WordForm()
    }
}