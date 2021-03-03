package com.example.mydict.db


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mydict.dao.CategoryDao
import com.example.mydict.dao.WordDao
import com.example.mydict.models.Category
import com.example.mydict.models.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class, Category::class], version = 1, exportSchema = false)
abstract class DictRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun categoryDao(): CategoryDao




    companion object{
        @Volatile private var instance:DictRoomDatabase? = null
         fun getInstance(context: Context, scope:CoroutineScope): DictRoomDatabase {
            return instance ?: synchronized(this){
                val db= Room.databaseBuilder(context.applicationContext,
                DictRoomDatabase::class.java, "dict_database")
                    .fallbackToDestructiveMigration()
                    .build()
                instance=db
                db
            }
         }
    }
}