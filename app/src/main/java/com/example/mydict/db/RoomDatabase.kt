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


    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            instance?.let { database ->
                scope.launch {
                    var categoryDao=database.categoryDao()

                    var category=Category("Nouns", 50)
                    Log.d("TEST", "testing...")
                    categoryDao.insert(category)

                }
            }
        }
    }

    companion object{
        @Volatile private var instance:DictRoomDatabase? = null
         fun getInstance(context: Context, scope:CoroutineScope): DictRoomDatabase {
            return instance ?: synchronized(this){
                val db= Room.databaseBuilder(context.applicationContext,
                DictRoomDatabase::class.java, "dict_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                instance=db
                db
            }
         }
    }
}