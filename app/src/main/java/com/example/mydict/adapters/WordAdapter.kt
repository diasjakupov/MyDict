package com.example.mydict.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydict.R
import com.example.mydict.models.Word

class WordAdapter ():
        RecyclerView.Adapter<WordAdapter.Holder>() {
    private val width= Resources.getSystem().displayMetrics.widthPixels
    private var words:List<Word> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).inflate(
                R.layout.word_list_item,
                parent,
                false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(words[position])
    }

    override fun getItemCount(): Int {
        return words.count()
    }

    fun refreshData(words: List<Word>){
        this.words=words
        notifyDataSetChanged()
    }

    inner class Holder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
        private val wordProgress: TextView =itemView.findViewById<TextView>(R.id.WordProgress)
        private val wordName: TextView =itemView.findViewById<TextView>(R.id.WordName)
        private val wordTranslate: TextView =itemView.findViewById<TextView>(R.id.WordTranslate)
        fun bindView(word: Word){
            wordName.text=word.name
            wordTranslate.text=word.translate
            wordProgress.text="${word.progress}%"
        }
    }


}
