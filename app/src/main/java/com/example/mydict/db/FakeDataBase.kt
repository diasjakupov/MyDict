package com.example.mydict.db

import com.example.mydict.models.Category
import kotlin.random.Random

object FakeDataBase {
    val categories= listOf<Category>(
            Category("Phrasal verbs", 38),
            Category("Adjectives", 89),
            Category("Irregular Verbs", 45),

    )
}