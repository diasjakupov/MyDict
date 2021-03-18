package com.example.mydict.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mydict.R
import com.example.mydict.activities.WordGame
import com.example.mydict.viewmodels.CategoryViewModel

class ButtonsFragment : Fragment() {
    private lateinit var Wordbtn: ImageButton
    private lateinit var CategoryBtn: ImageButton
    private lateinit var StartGame:ImageButton


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
        CategoryBtn=view.findViewById(R.id.CategoryAdd)
        CategoryBtn.setOnClickListener {
            onCategoryClick()
        }
        StartGame=view.findViewById(R.id.startGame)
        StartGame.setOnClickListener {
            val intent=Intent(activity, WordGame::class.java)
            startActivity(intent)
        }
        
    }

    private fun onWordClick(){
        val dialog=WordForm.newInstance()
        dialog.show(childFragmentManager, "wordForm")
    }

    private fun onCategoryClick(){
        val dialog=CategoryForm.newInstance()
        dialog.show(childFragmentManager, "categoryForm")
    }
    companion object {
        @JvmStatic
        fun newInstance() = ButtonsFragment()
    }
}