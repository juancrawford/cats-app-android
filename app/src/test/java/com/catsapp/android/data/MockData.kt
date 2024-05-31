package com.catsapp.android.data

import com.catsapp.android.model.Cat

object MockData {
    val mockCat1 = Cat(breeds = listOf(), id = "1", url = "https://example.com/cat1.jpg", width = 100, height = 100)
    val mockCat2 = Cat(breeds = listOf(), id = "2", url = "https://example.com/cat2.jpg", width = 100, height = 100)
    val mockCat3 = Cat(breeds = listOf(), id = "3", url = "https://example.com/cat3.jpg", width = 100, height = 100)
    val mockCats = listOf(mockCat1, mockCat2, mockCat3)
}