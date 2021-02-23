package com.example.mydict.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
class Category(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "progress") var progress: Int = 0,
){
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    override fun toString(): String {
        return title
    }
}