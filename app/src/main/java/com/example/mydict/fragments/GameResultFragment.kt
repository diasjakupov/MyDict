package com.example.mydict.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.mydict.DictApplication
import com.example.mydict.R
import com.example.mydict.activities.MainActivity
import com.example.mydict.models.Word
import com.example.mydict.viewmodels.GameViewModel
import com.example.mydict.viewmodels.GameViewModelProvider
import kotlin.math.roundToInt
import kotlin.properties.Delegates


class GameResultFragment : Fragment() {
    private lateinit var resultPercent:TextView
    private lateinit var knownWords:TextView
    private lateinit var goToMain:Button
    private val viewModel: GameViewModel by activityViewModels {
        GameViewModelProvider((requireActivity().application as DictApplication).repository)
    }
    private var amount by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amount= arguments?.getInt("AMOUNT")!!
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_result, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultPercent=view.findViewById(R.id.ResultPercent)
        knownWords=view.findViewById(R.id.KnownWords)
        goToMain=view.findViewById(R.id.goToMainPage)
        val percent= (
                viewModel.knownWords.value?.toDouble()?.div(amount.toDouble())
                )?.times(100)?.roundToInt()

        resultPercent.text="Result: $percent"
        knownWords.text="You know ${viewModel.knownWords.value} of $amount"
        goToMain.setOnClickListener {
            val intent= Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(words:ArrayList<Word>) = GameResultFragment().apply { arguments=Bundle().apply {
            putInt("AMOUNT", words.size)
        } }
    }
}