package com.example.mydict.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.models.Category
import com.example.mydict.models.StatusModelChange
import com.example.mydict.viewmodels.CategoryViewModel
import com.example.mydict.viewmodels.CategoryViewModelProvider



class DeleteOrUpdateCategory : DialogFragment() {
    private lateinit var delete:TextView
    private lateinit var cancel:TextView
    private lateinit var update:TextView
    private val viewModel: CategoryViewModel by activityViewModels {
        CategoryViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private lateinit var category: Category

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.fragment_delete_or_update_category, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        category= arguments?.getParcelable("CATEGORY")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delete=view.findViewById(R.id.delete_confirm)
        cancel=view.findViewById(R.id.cancel_confirm)
        update=view.findViewById(R.id.update_confirm)

        delete.setOnClickListener {
            viewModel.delete(category.id)
            dismiss()
        }
        update.setOnClickListener {
            AddBottomWindow.newInstance(category, StatusModelChange.UPDATE_CATEGORY)
                .show(childFragmentManager, "ADD BOTTOM WINDOW")
            dismiss()
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(category: Category):DeleteOrUpdateCategory{
            val frag=DeleteOrUpdateCategory()
            var bundle=Bundle()
            bundle.putParcelable("CATEGORY", category)
            frag.arguments=bundle
            return frag
        }
    }
}