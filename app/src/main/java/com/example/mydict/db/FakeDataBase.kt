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

    val words = listOf<Word>(
            Word("Transcend",
                    "Превосходить",
                    "I need my powers to transcend the limitations of your magic.",
                    0,
                    2),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    3),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    2),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    1), Word("Transcend",
            "Превосходить",
            "I need my powers to transcend the limitations of your magic.",
            0,
            0),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    3),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    2),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    1), Word("Transcend",
            "Превосходить",
            "I need my powers to transcend the limitations of your magic.",
            0,
            0),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    3),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    2),
            Word("Surpass",
                    "Превосходить",
                    "By 2050, the global population will surpass nine billion.",
                    83,
                    1)
    )


}