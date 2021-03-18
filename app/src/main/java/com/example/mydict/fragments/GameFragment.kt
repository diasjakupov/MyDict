package com.example.mydict.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.activities.MainActivity
import com.example.mydict.models.Category
import com.example.mydict.models.UpdateWordStatus
import com.example.mydict.models.Word
import com.example.mydict.viewmodels.GameViewModel
import com.example.mydict.viewmodels.GameViewModelProvider
import kotlin.properties.Delegates


class GameFragment : Fragment() {
    private lateinit var category:Category
    private val viewModel:GameViewModel by activityViewModels {
        GameViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private var words:ArrayList<Word>?=null
    private lateinit var okBtn: ImageButton
    private lateinit var notBtn: ImageButton
    private lateinit var bottomMenu:LinearLayout
    private lateinit var wordCount:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category= arguments?.getParcelable<Category>("CATEGORY")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordCount=view.findViewById(R.id.word_count)
        okBtn=view.findViewById(R.id.word_game_ok)
        notBtn=view.findViewById(R.id.word_game_not)
        bottomMenu=view.findViewById(R.id.bottom_game_menu)


        viewModel.words(category.id).observe(viewLifecycleOwner, {
            if(words?.map { item->item.name }?.sorted()!=it.map { item->item.name }.sorted()){
                words=(it.shuffled() as ArrayList<Word>)
            }else{
                Log.e("TESTING", "EQUALS")
            }
            viewModel.count.observe(viewLifecycleOwner, {count->
                if(count<it.size){
                    wordCount.text="${(it.size-count)} word(-s)"
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.word_game, CardFragment.newInstance(words!![count])).commit()
                }else{
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.word_game, GameResultFragment.newInstance(words!!)).commit()
                }
            })

        })

        viewModel.isChecked.observe(viewLifecycleOwner, {
            if(it){
                bottomMenu.visibility=VISIBLE
            }
            else{
                bottomMenu.visibility=GONE
            }
        })

        okBtn.setOnClickListener {
            viewModel.updateWordProgress(words!![viewModel.count.value!!],
                UpdateWordStatus.OK,
                category.id)
            viewModel.knownWords.value=viewModel.knownWords.value?.plus(1)
            viewModel.increaseCount()
        }
        notBtn.setOnClickListener {
            viewModel.updateWordProgress(words!![viewModel.count.value!!],
                UpdateWordStatus.BAD,
                category.id)
            viewModel.increaseCount()
        }
    }




    companion object {

        @JvmStatic
        fun newInstance(category: Category) = GameFragment().apply {
            val bundle= Bundle()
            bundle.putParcelable("CATEGORY", category)
            arguments=bundle
        }
    }
}