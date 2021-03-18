package com.example.mydict.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExampleConvert {
    @TypeConverter
    fun fromExample(examples: ArrayList<String>): String {
        return Gson().toJson(examples)
    }

    @TypeConverter
    fun toExample(examples: String): ArrayList<String>? {
        return Gson()
            .fromJson(examples, object : TypeToken<ArrayList<String>>() {}.type)
    }
}

@Entity(tableName = "word_table")
@TypeConverters(ExampleConvert::class)
data class Word(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "translate") var translate: String,
    var example: ArrayList<String>,
    @ColumnInfo(name = "progress") var progress: Int = 0,
    @ColumnInfo(name = "category") var category: Int
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        arrayListOf<String>().apply {
            parcel.readList(this, String::class.java.classLoader)
        },
        parcel.readInt(),
        parcel.readInt()
    ) {
        id = parcel.readInt()
    }

    override fun toString(): String {
        return name
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(translate)
        parcel.writeList(example)
        parcel.writeInt(progress)
        parcel.writeInt(category)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Word> {
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }


}