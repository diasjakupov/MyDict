package com.example.mydict

import android.app.Application
import com.example.mydict.db.DictRepository
import com.example.mydict.db.DictRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DictApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database: DictRoomDatabase by lazy { DictRoomDatabase.getInstance(this,
        applicationScope) }
    val repository: DictRepository by lazy { DictRepository(
        database.wordDao(),
        database.categoryDao()
    ) }
}