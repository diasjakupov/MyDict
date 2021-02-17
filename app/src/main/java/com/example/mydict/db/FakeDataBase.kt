package com.example.mydict.db

import com.example.mydict.models.Category
import com.example.mydict.models.Word

object FakeDataBase {
    val categories= listOf<Category>(
            Category("Phrasal verbs", 38,0),
            Category("Adjectives", 89,1),
            Category("Irregular Verbs", 45,2),
    )
    val words= listOf<Word>(
            Word("Transcend",
                    "Превосходить",
                    "I need my powers to transcend the limitations of your magic.",
                    0,
                    categories[0]),
            Word("Transcend",
                    "Превосходить",
                            "I need my powers to transcend the limitations of your magic.",
                            0,
                            categories[0])
    )
}