package com.example.mydict.models

enum class CategoryAdapter(){
    MainCategoryAdapter, ChooseCategory
}

enum class UpdateWordStatus(){
    OK, BAD
}

enum class StatusModelChange {
    CREATE_EXAMPLE, UPDATE_EXAMPLE, UPDATE_CATEGORY
}
