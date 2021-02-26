package com.example.mydict.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "progress") var progress: Int = 0,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    override fun toString(): String {
        return title
    }
}