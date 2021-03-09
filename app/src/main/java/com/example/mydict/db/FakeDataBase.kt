package com.example.mydict.db

import com.example.mydict.models.Category
import com.example.mydict.models.Word

object FakeDataBase {
    val categories = mutableListOf<Category>(
            Category("Phrasal verbs", 38),
            Category("Adjectives", 89),
            Category("Irregular Verbs", 45),
            Category("Nouns", 83)
    )

    val words = listOf<Word>()



}