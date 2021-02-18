package com.example.mydict.models

import android.os.Parcel
import android.os.Parcelable


class Category(var title: String, var progress: Int = 0, val id:Int){
    override fun toString(): String {
        return title
    }
}