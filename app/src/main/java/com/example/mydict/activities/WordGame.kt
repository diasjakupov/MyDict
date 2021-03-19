package com.example.mydict.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDelegate
import com.example.mydict.R
import com.example.mydict.fragments.ChooseCategory


class WordGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_word_game)
        supportFragmentManager.beginTransaction().replace(
            R.id.flGame, ChooseCategory.newInstance()
        ).commit()
    }
}