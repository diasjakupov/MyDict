package com.example.mydict.fragments


import android.content.res.Resources
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.example.mydict.R

class WordForm : DialogFragment() {

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
        var screenHeight= Resources.getSystem().displayMetrics.heightPixels
        var screenWeight=Resources.getSystem().displayMetrics.widthPixels
        var cardView=view.findViewById<CardView>(R.id.cardView)
        Log.e("CONF", "$screenHeight")
        Log.e("CONF", "$screenWeight")
        cardView.layoutParams.width=(screenWeight.toDouble()*0.7).toInt()
        cardView.layoutParams.height=(screenHeight.toDouble()*0.3).toInt()
    }


    companion object {
        @JvmStatic
        fun newInstance() = WordForm()
    }
}