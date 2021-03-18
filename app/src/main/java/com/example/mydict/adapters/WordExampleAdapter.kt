package com.example.mydict.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.R

class WordExampleAdapter(var itemOnDelete:(String, id:Int)->Unit,
                         var itemOnChange:(String, id:Int)->Unit)
    : RecyclerView.Adapter<WordExampleAdapter.Holder>() {
    private var examples: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(parent.context).inflate(
            R.layout.example, parent, false
        )
        return Holder(view, itemOnDelete, itemOnChange)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onDataBind(examples[position], position)
    }

    override fun getItemCount(): Int {
        return examples.size
    }

    fun refreshData(data:ArrayList<String>){
        this.examples=data
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View,
                       var itemOnDelete:(String, id:Int)->Unit,
                       var itemOnChange:(String, id:Int)->Unit) : RecyclerView.ViewHolder(itemView){
        private var text:TextView=itemView.findViewById(R.id.exampletext)
        private var onDelete:ImageView=itemView.findViewById(R.id.delete_example)
        private var onChange:ImageView=itemView.findViewById(R.id.edit_example)

        fun onDataBind(example:String, position:Int){
            text.text=example
            onDelete.setOnClickListener {
                itemOnDelete(example, position)
            }
            onChange.setOnClickListener {
                itemOnChange(example, position)
            }
        }
    }
}