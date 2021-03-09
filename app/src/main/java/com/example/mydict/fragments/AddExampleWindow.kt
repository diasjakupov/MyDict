package com.example.mydict.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.viewmodels.WordDetailViewModel
import com.example.mydict.viewmodels.WordDetailViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddExampleWindow(var wordId:Int) : BottomSheetDialogFragment() {
    private lateinit var addCheck:ImageButton
    private lateinit var exampleText: EditText
    private val viewModel:WordDetailViewModel by activityViewModels {
        WordDetailViewModelProvider((requireActivity().application as DictApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_example_window, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCheck=view.findViewById(R.id.addExampleCheck)
        exampleText=view.findViewById(R.id.exampleCreate)

        addCheck.setOnClickListener {
            viewModel.insertExamples(exampleText.text.toString(), id = wordId)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id:Int) = AddExampleWindow(id)
    }
}