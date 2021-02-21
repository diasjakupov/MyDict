package com.example.mydict.fragments


import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.example.mydict.R

class WordForm : DialogFragment() {
    private lateinit var createNewWord:TextView
    private lateinit var wordInput:TextView
    private lateinit var wordTranslate:TextView

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
        var screenWeight=Resources.getSystem().displayMetrics.widthPixels
        var cardView=view.findViewById<CardView>(R.id.cardView)
        cardView.layoutParams.width=(screenWeight.toDouble()*0.8).toInt()

        createNewWord=view.findViewById(R.id.addWord)
        wordInput=view.findViewById(R.id.word)
        wordTranslate=view.findViewById(R.id.wordTranslate)
        createNewWord.setOnClickListener {
            if(wordInput.text.toString().isNotEmpty() &&
                    wordTranslate.text.toString().isNotEmpty()){
                dialog?.dismiss()
            }else{
                Toast.makeText(activity?.baseContext,
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