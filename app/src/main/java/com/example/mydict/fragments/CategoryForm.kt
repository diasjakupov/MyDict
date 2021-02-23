package com.example.mydict.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.example.mydict.R

class CategoryForm : DialogFragmentInstance() {
    private lateinit var categoryEdit:EditText
    private lateinit var addCategoryBtn: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryEdit=view.findViewById(R.id.CategryEdit)
        addCategoryBtn=view.findViewById(R.id.addCategory)
        cardView=view.findViewById(R.id.CategoryCardView)
        getCardViewWeight()

        addCategoryBtn.setOnClickListener {
            if(categoryEdit.text.toString().isNotEmpty()){
                dialog?.dismiss()
            }else{
                Toast.makeText(activity?.baseContext,
                    "Please fill up the input(-s)",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() = CategoryForm()
    }
}