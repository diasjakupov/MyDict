package com.example.mydict.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.models.Category
import com.example.mydict.models.Word
import com.example.mydict.viewmodels.GameViewModel
import com.example.mydict.viewmodels.GameViewModelProvider
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout


class CardFragment : Fragment() {
    private lateinit var word:Word
    private val viewModel:GameViewModel by activityViewModels {
        GameViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private lateinit var wordName:TextView
    private lateinit var wordTranslate:TextView
    private lateinit var questionMark:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        word=arguments?.getParcelable<Word>("WORD")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordName=view.findViewById(R.id.word_game_name)
        wordTranslate=view.findViewById(R.id.word_game_translate)
        questionMark=view.findViewById(R.id.question_mark)

        bindWordData()

        questionMark.setOnClickListener {
            onQuestionMarkClick()
        }
    }

    private fun bindWordData(){
        wordName.text=word.name
        wordTranslate.text=word.translate

    }

    private fun onQuestionMarkClick(){
        questionMark.visibility= GONE
        wordTranslate.visibility= VISIBLE
        viewModel.isChecked.value=true
    }


    companion object {
        @JvmStatic
        fun newInstance(word:Word) = CardFragment().apply {
            arguments=Bundle().apply { putParcelable("WORD",word) }
        }
    }
}