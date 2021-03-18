package com.example.mydict.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.R
import com.example.mydict.models.Category
import com.example.mydict.models.CategoryAdapter


class RecyclerCategoryAdapter(private var typeOfAdapter: CategoryAdapter):
        RecyclerView.Adapter<HolderI>() {

    private lateinit var onItemClick: (Category)->Unit
    private lateinit var onLongItemClick:(Category)->Boolean

    constructor(typeOf: CategoryAdapter,
                onClick: (Category)->Unit,
                onLongClick:(Category)->Boolean) : this(typeOf){
        this.onItemClick =onClick
        this.onLongItemClick=onLongClick
    }

    constructor(typeOf: CategoryAdapter,
                onClick: (Category)->Unit) : this(typeOf){
        this.onItemClick =onClick
    }

    private var categories:List<Category> = mutableListOf()
    private val width= Resources.getSystem().displayMetrics.widthPixels

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderI {
        when(typeOfAdapter){
            CategoryAdapter.MainCategoryAdapter -> {
                val view=LayoutInflater.from(parent.context).inflate(
                    R.layout.category_item,
                    parent,
                    false)
                return HolderMain(view, onItemClick, onLongItemClick)
            }
            CategoryAdapter.ChooseCategory->{
            val view=LayoutInflater.from(parent.context).inflate(
                R.layout.spinner_text,
                parent,
                false)
            return HolderGame(view, onItemClick)
            }
        }

    }

    override fun onBindViewHolder(holder: HolderI, position: Int) {
        holder.bindView(categories[position])
    }


    override fun getItemCount(): Int {
        return categories.count()
    }


    fun refreshDataList(categories: List<Category>){
        this.categories=categories
        notifyDataSetChanged()
    }


    inner class HolderMain(
        itemView: View,
        var onItemClick: (Category) -> Unit,
        var onLongItemClick:(Category)->Boolean) :
        HolderI(itemView) {

        private val catProgress: ImageView =itemView.findViewById<ImageView>(R.id.progress)
        private val catName: TextView =itemView.findViewById<TextView>(R.id.cat_name)

        override fun bindView(category: Category){

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
            itemView.setOnLongClickListener { onLongItemClick(category) }
        }
    }

    inner class HolderGame(itemView: View, var onItemClick: (Category) -> Unit): HolderI(itemView) {
        private val CategoryText: TextView =itemView.findViewById<TextView>(R.id.CategoryText)

        override fun bindView(category: Category) {
            CategoryText.text=category.title
            CategoryText.setOnClickListener { onItemClick(category) }
        }
    }
}
