package com.example.mydict.models

class Word(
        var name:String,
        var translate:String,
        var example:String,
        var progress: Int = 0) {
    override fun toString(): String {
        return name
    }
}