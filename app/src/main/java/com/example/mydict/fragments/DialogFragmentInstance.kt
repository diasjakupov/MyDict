package com.example.mydict.fragments

import android.content.res.Resources
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment

abstract class DialogFragmentInstance: DialogFragment() {
    private var screenWeight= Resources.getSystem().displayMetrics.widthPixels
    protected lateinit var cardView: CardView

    fun getCardViewWeight(){
        cardView.layoutParams.width=(screenWeight.toDouble()*0.8).toInt()
    }
}