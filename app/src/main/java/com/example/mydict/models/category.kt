package com.example.mydict.models

class Category(var title:String, var progress: Int = 0){
    override fun toString(): String {
        return title
    }
}