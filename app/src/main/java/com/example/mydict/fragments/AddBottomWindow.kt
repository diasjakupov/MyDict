package com.example.mydict.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.models.Category
import com.example.mydict.models.StatusModelChange
import com.example.mydict.viewmodels.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddBottomWindow() : BottomSheetDialogFragment() {
    private lateinit var addCheck: ImageButton
    private lateinit var exampleText: EditText
    private val wordDetailViewModel: WordDetailViewModel by activityViewModels {
        WordDetailViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private val categoryDetailViewModel: CategoryViewModel by activityViewModels {
        CategoryViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private var statusModelChange: StatusModelChange? = null
    private lateinit var text: String
    private var wordId: Int? = null
    private var positionExample: Int? = null
    private var category:Category?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_example_window, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCheck = view.findViewById(R.id.addExampleCheck)
        exampleText = view.findViewById(R.id.exampleCreate)
        statusModelChange = arguments?.let { StatusModelChange.valueOf(it.getString("STATUS", StatusModelChange.CREATE_EXAMPLE.name)) }
        text = arguments?.getString("TEXT", "").toString()
        wordId = arguments?.getInt("WORD_ID", 0)
        positionExample = arguments?.getInt("EXAMPLE_POSITION")
        category=arguments?.getParcelable("CATEGORY")


        when(statusModelChange){
            StatusModelChange.UPDATE_EXAMPLE->exampleText.setText(text)
            StatusModelChange.UPDATE_CATEGORY->exampleText.setText(category!!.title)
            else -> exampleText.text = null
        }

        setOnClickListener()
    }

    fun setOnClickListener(){
        addCheck.setOnClickListener {
            when (statusModelChange) {
                StatusModelChange.CREATE_EXAMPLE -> {
                    wordDetailViewModel
                        .insertExamples(
                            exampleText.text.toString(),
                            id = wordId!!
                        )
                    dismiss()
                }
                StatusModelChange.UPDATE_EXAMPLE -> {
                    wordDetailViewModel.updateExample(
                        positionExample!!,
                        wordId!!,
                        exampleText.text.toString()
                    )
                    dismiss()
                }
                StatusModelChange.UPDATE_CATEGORY ->{
                    categoryDetailViewModel.updateCategory(
                        category_id = category!!.id,
                        name=exampleText.text.toString()
                    )
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            id: Int,
            statusModelChange: StatusModelChange,
            positionExample: Int? = null,
            text: String? = null
        ): AddBottomWindow {
            val fragment = AddBottomWindow()
            val newBundle = Bundle().apply {
                putInt("WORD_ID", id)
                if (positionExample != null) {
                    putInt("EXAMPLE_POSITION", positionExample)
                }
                putString("STATUS", statusModelChange.name)
                putString("TEXT", text)
            }

            fragment.arguments = newBundle

            return fragment
        }

        fun newInstance(
            category:Category,
            statusModelChange: StatusModelChange)=AddBottomWindow().apply {
            arguments=Bundle().apply {
                putParcelable("CATEGORY", category)
                putString("STATUS", statusModelChange.name)
            }
        }
    }
}