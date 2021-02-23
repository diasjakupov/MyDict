package com.example.mydict.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "translate") var translate:String,
    @ColumnInfo(name = "example") var example:String,
    @ColumnInfo(name = "progress") var progress: Int = 0,
    @ColumnInfo(name = "category") var category: Int)
{
    @PrimaryKey(autoGenerate = true) val id: Int = 0

    override fun toString(): String {
        return name
    }
}