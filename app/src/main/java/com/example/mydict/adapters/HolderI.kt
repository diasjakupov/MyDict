package com.example.mydict.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.models.Category

abstract class HolderI(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(category: Category)
}