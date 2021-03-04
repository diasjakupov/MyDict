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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.models.Category
import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider

class CategoryForm : DialogFragmentInstance() {
    private lateinit var categoryEdit:EditText
    private lateinit var addCategoryBtn: TextView
    val viewModel: CategoryViewModel by activityViewModels{
        CategoryViewModelProvider((activity?.application as DictApplication).repository)
    }

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
                viewModel.insertCategory(
                    Category(title = categoryEdit.text.toString(), progress = 0)
                )
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