package com.example.mydict.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mydict.R

class AddButtonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_button, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddButtonFragment()
    }
}