package com.example.mydict.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydict.dao.WordDao

import com.example.mydict.models.Category
import com.example.mydict.models.Word

@Database(entities = [Word::class, Category::class], version = 1, exportSchema = false)
abstract class DictRoomDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object{
        @Volatile private var instance:DictRoomDatabase? = null
         fun getInstance(context: Context): DictRoomDatabase {
            return instance ?: synchronized(this){
                val db= Room.databaseBuilder(context.applicationContext,
                DictRoomDatabase::class.java, "dict_database").build()
                instance=db
                db
            }
         }
    }
}