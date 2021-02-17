package com.example.mydict.adapters



import android.content.Context
import android.media.Image
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mydict.R
import com.example.mydict.models.Category

class CategoryAdapter(private val context: Context,
                      private val data: List<Category>,
                      private var width: Int): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val catView:View=LayoutInflater.from(context).inflate(R.layout.category_item,
                parent,
                false)

        val cat_progress=catView.findViewById<ImageView>(R.id.progress)
        val cat_name=catView.findViewById<TextView>(R.id.cat_name)
        val category=data[position]

        val imageWidth=if(category.progress > 0 ) {
                (width * category.progress)/100
        }else 0
        cat_progress.setImageResource(when(category.progress){
            in 0..40->R.drawable.category_progress_bg_red
            in 41..70->R.drawable.category_progress_bg_orange
            else -> R.drawable.category_progress_bg_green
        })

        cat_progress.layoutParams.width=imageWidth

        cat_name.text=category.title
        return catView
    }

    override fun getCount(): Int {
        return data.count()
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }



}