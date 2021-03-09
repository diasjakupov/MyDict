package com.example.mydict.models

import android.util.Log
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.RuntimeException

class ExampleConvert{
    @TypeConverter
    fun fromExample(examples: ArrayList<String>?): String? {
            return Gson().toJson(examples)


    }

    @TypeConverter
    fun toExample(examples:String): ArrayList<String>? {
        try {
            var arrList= Gson()
                .fromJson<ArrayList<String>>(examples, object : TypeToken<ArrayList<String>>(){}.type)
            arrList= arrList.map { it.replace("_", " ") } as ArrayList<String>?
            return arrList
        }catch (e: RuntimeException){
            return null
        }

    }
}

@Entity(tableName = "word_table")
@TypeConverters(ExampleConvert::class)
data class Word(
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "translate") var translate:String,

    var example:ArrayList<String>?,
    @ColumnInfo(name = "progress") var progress: Int = 0,
    @ColumnInfo(name = "category") var category: Int)
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    override fun toString(): String {
        return name
    }


}