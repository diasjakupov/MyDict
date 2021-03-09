package com.example.mydict.adapters

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.R
import com.example.mydict.models.Category


class RecyclerCategoryAdapter(var onItemClick: (Category)->Unit):
        RecyclerView.Adapter<RecyclerCategoryAdapter.Holder>() {
    private var categories:List<Category> = mutableListOf()
    private val width= Resources.getSystem().displayMetrics.widthPixels

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent,
                false)
        return Holder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    fun refreshDataList(categories: List<Category>){
        this.categories=categories
        notifyDataSetChanged()
    }

    inner class Holder(private val itemView: View, var onItemClick: (Category) -> Unit) : RecyclerView.ViewHolder(itemView){
        private val catProgress: ImageView =itemView.findViewById<ImageView>(R.id.progress)
        private val catName: TextView =itemView.findViewById<TextView>(R.id.cat_name)

        fun bindView(category: Category){
            val imageWidth=if(category.progress > 0 ) {
                (width * category.progress)/100
            }else 0
            catProgress.setImageResource(when(category.progress){
                0->R.drawable.category_progress_bg_grey
                in 1..40-> R.drawable.category_progress_bg_red
                in 41..70-> R.drawable.category_progress_bg_orange
                else -> R.drawable.category_progress_bg_green
            })
            catProgress.layoutParams.width=imageWidth
            catName.text=category.title

            itemView.setOnClickListener { onItemClick(category) }
        }
    }
}
