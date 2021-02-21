package com.example.mydict.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mydict.R
import com.example.mydict.viewmodels.CategoryViewModel

class ButtonsFragment : Fragment() {
    lateinit var Wordbtn: ImageButton
    lateinit var CategoryBtn: ImageButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Wordbtn=view.findViewById(R.id.addButton)
        Wordbtn.setOnClickListener {
            onWordClick()
        }
    }

    private fun onWordClick(){
        val dialog=WordForm()
        dialog.show(childFragmentManager, "wordForm")
    }
    companion object {
        @JvmStatic
        fun newInstance() = ButtonsFragment()
    }
}